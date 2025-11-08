package com.sarim.sidebar_dates_data.repository

import androidx.datastore.core.DataStore
import com.sarim.sidebar_dates_data.model.DateDto
import com.sarim.sidebar_dates_data.model.DateDto.Companion.fromDate
import com.sarim.sidebar_dates_data.model.DateDto.Companion.toDate
import com.sarim.sidebar_dates_data.model.DateDtoDao
import com.sarim.sidebar_dates_domain.model.Date
import com.sarim.sidebar_dates_domain.repository.SidebarDatesRepository
import com.sarim.utils.R
import com.sarim.utils.ui.MessageType
import com.sarim.utils.ui.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException

const val TOTAL_MOCK_ITEMS = 10

class SidebarDatesRepositoryImpl(
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val dataStore: DataStore<DateDto>,
    private val dataStoreName: String,
    private val dao: DateDtoDao,
) : SidebarDatesRepository {
    init {
        CoroutineScope(ioDispatcher).launch {
            val existing = dao.getAll().firstOrNull()
            if (existing.isNullOrEmpty()) {
                val initialDates =
                    List(TOTAL_MOCK_ITEMS) { index ->
                        DateDto(
                            dateHeading = "Date ${index + 1}",
                            dateSessions = 10,
                        )
                    }
                dao.insertAll(initialDates)
            }
        }
    }

    override val dates: Flow<Resource<List<Date>>> =
        dao
            .getAll()
            .map { dateDtoList ->
                try {
                    Resource.Success(dateDtoList.map { it.toDate() })
                } catch (
                    @Suppress("TooGenericExceptionCaught") e: Exception,
                ) {
                    Resource.Error(
                        message =
                            e.localizedMessage?.let { MessageType.StringMessage(it) }
                                ?: MessageType.IntMessage(R.string.unknown_reason_exception),
                    )
                }
            }.catch { e ->
                emit(
                    Resource.Error(
                        message =
                            e.localizedMessage?.let { MessageType.StringMessage(it) }
                                ?: MessageType.IntMessage(R.string.unknown_reason_read_exception),
                    ),
                )
            }

    override suspend fun selectDate(date: Date) =
        try {
            println("trying to select date")
            dataStore.updateData {
                date.fromDate()
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

    override fun getSelectedDate() =
        dataStore.data
            .map {
                println("GOT SELECTED DATE!")
                Resource.Success(it.toDate()) as Resource<Date>
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

    override suspend fun getDatesAccordingToSearchFilter(searchFilter: String) =
        dao
            .getDateDtosAccordingToHeading(searchFilter)
            .map { dateDtoList ->
                try {
                    Resource.Success(dateDtoList.map { it.toDate() })
                } catch (
                    @Suppress("TooGenericExceptionCaught") e: Exception,
                ) {
                    Resource.Error(
                        message =
                            e.localizedMessage?.let { MessageType.StringMessage(it) }
                                ?: MessageType.IntMessage(R.string.unknown_reason_exception),
                    )
                }
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
