package com.sarim.sidebar_data.repository

import com.sarim.sidebar_data.model.DateDto
import com.sarim.sidebar_data.model.SessionDto
import com.sarim.sidebar_data.model.toDate
import com.sarim.sidebar_data.model.toSession
import com.sarim.sidebar_domain.model.Date
import com.sarim.sidebar_domain.repository.SidebarRepository
import com.sarim.utils.ui.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.UUID

const val TOTAL_MOCK_ITEMS = 10

class SidebarRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : SidebarRepository {
    override val dates: Flow<Resource<List<Date>>> =
        flow {
            val mockDates =
                List(TOTAL_MOCK_ITEMS) { index ->
                    DateDto(
                        id = UUID.randomUUID().toString(),
                        dateHeading = "Date ${index + 1}",
                        dateSessions = 10,
                    )
                }
            emit(Resource.Success(mockDates.map { it.toDate() }))
        }.flowOn(ioDispatcher)

    override fun getSessions(date: Date?) =
        flow {
            if (date == null) return@flow

            val mockSessions =
                List(TOTAL_MOCK_ITEMS) { index ->
                    SessionDto(
                        id = UUID.randomUUID().toString(),
                        sessionHeading = "${date.dateHeading} Session ${index + 1}",
                        sessionLogs = 9999,
                    )
                }
            emit(Resource.Success(mockSessions.map { it.toSession() }))
        }.flowOn(ioDispatcher)
}
