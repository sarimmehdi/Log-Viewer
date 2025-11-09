package com.sarim.sidebar_sessions_data.repository

import androidx.datastore.core.DataStore
import com.sarim.sidebar_dates_data.model.DateDto
import com.sarim.sidebar_dates_data.model.DateDtoDao
import com.sarim.sidebar_sessions_data.model.SessionDto
import com.sarim.sidebar_sessions_data.model.SessionDto.Companion.fromSession
import com.sarim.sidebar_sessions_data.model.SessionDto.Companion.toSession
import com.sarim.sidebar_sessions_data.model.SessionDtoDao
import com.sarim.sidebar_sessions_domain.model.Session
import com.sarim.sidebar_sessions_domain.repository.SidebarSessionsRepository
import com.sarim.utils.R
import com.sarim.utils.ui.MessageType
import com.sarim.utils.ui.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.collections.map

const val TOTAL_MOCK_ITEMS = 10

class SidebarSessionsRepositoryImpl(
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val sessionDtoDataStore: DataStore<SessionDto>,
    private val datesDtoDataStore: DataStore<DateDto>,
    private val dataStoreName: String,
    private val dateDtoDao: DateDtoDao,
    private val sessionDtoDao: SessionDtoDao,
) : SidebarSessionsRepository {
    init {
        CoroutineScope(ioDispatcher).launch {
            dateDtoDao.getAll().collectLatest { dateDtos ->
                dateDtos.forEach { dateDto ->
                    val existing =
                        sessionDtoDao.getAllSessionsForDateId(dateDto.dateId).firstOrNull()
                    if (existing.isNullOrEmpty()) {
                        val initialSessions =
                            List(TOTAL_MOCK_ITEMS) { index ->
                                SessionDto(
                                    dateId = dateDto.dateId,
                                    sessionHeading = "Session ${index + 1}",
                                    sessionLogs = 10,
                                )
                            }
                        sessionDtoDao.insertAll(initialSessions)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getSessions() =
        datesDtoDataStore.data
            .filter { it != DateDto.NO_SELECTED_DATE_DTO }
            .map { it.dateId }
            .flatMapLatest { dateId ->
                sessionDtoDao
                    .getAllSessionsForDateId(dateId)
                    .map { sessionDtos ->
                        Resource.Success(sessionDtos.map { it.toSession() }) as Resource<List<Session>>
                    }.catch { e ->
                        emit(
                            Resource.Error(
                                message =
                                    e.localizedMessage?.let { MessageType.StringMessage(it) }
                                        ?: MessageType.IntMessage(R.string.unknown_reason_read_exception),
                            ),
                        )
                    }
            }

    override suspend fun selectSession(session: Session) =
        try {
            sessionDtoDataStore.updateData {
                session.fromSession()
            }
            Resource.Success(true)
        } catch (e: IOException) {
            Resource.Error(
                message =
                    e.localizedMessage?.let {
                        MessageType.StringMessage(it)
                    } ?: MessageType.IntMessage(
                        R.string.unknown_reason_write_exception,
                        dataStoreName,
                        e,
                    ),
            )
        } catch (
            @Suppress("TooGenericExceptionCaught") e: Exception,
        ) {
            Resource.Error(
                message =
                    e.localizedMessage?.let {
                        MessageType.StringMessage(it)
                    } ?: MessageType.IntMessage(R.string.unknown_reason_exception, e),
            )
        }

    override fun getSelectedSession() =
        sessionDtoDataStore.data
            .map {
                Resource.Success(it.toSession()) as Resource<Session>
            }.catch { e ->
                emit(
                    Resource.Error(
                        message =
                            e.localizedMessage?.let {
                                MessageType.StringMessage(it)
                            } ?: MessageType.IntMessage(
                                R.string.unknown_reason_read_exception,
                                dataStoreName,
                                e,
                            ),
                    ),
                )
            }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getSessionsAccordingToSearchFilterForDate(searchFilter: String) =
        datesDtoDataStore.data
            .filter { it != DateDto.NO_SELECTED_DATE_DTO }
            .map { it.dateId }
            .flatMapLatest { dateId ->
                sessionDtoDao
                    .getSessionDtosAccordingToHeading(searchFilter, dateId)
                    .map { sessionDtoList ->
                        Resource.Success(sessionDtoList.map { it.toSession() }) as Resource<List<Session>>
                    }.catch { e ->
                        emit(
                            Resource.Error(
                                message =
                                    e.localizedMessage?.let { MessageType.StringMessage(it) }
                                        ?: MessageType.IntMessage(R.string.unknown_reason_read_exception),
                            ),
                        )
                    }
            }
}
