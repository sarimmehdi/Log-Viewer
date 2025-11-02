package com.sarim.sidebar_data.repository

import com.sarim.sidebar_data.model.DateDto
import com.sarim.sidebar_data.model.SessionDto
import com.sarim.sidebar_data.model.toDate
import com.sarim.sidebar_data.model.toSession
import com.sarim.sidebar_domain.model.Date
import com.sarim.sidebar_domain.model.Session
import com.sarim.sidebar_domain.repository.SidebarRepository
import com.sarim.utils.ui.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SidebarRepositoryImpl : SidebarRepository {
    override val dates: Flow<Resource<List<Date>>> =
        flow {
            val mockDates =
                List(10) {
                    DateDto(
                        dateHeading = "MMMMMMMMMMMMMMMMMMMMMMMMMMMM",
                        dateSessions = 10,
                    )
                }
            emit(Resource.Success(mockDates.map { it.toDate() }))
        }

    override val sessions: Flow<Resource<List<Session>>> =
        flow {
            val mockSessions =
                List(10) {
                    SessionDto(
                        sessionHeading = "MMMMMMMMMMMMMMMMMMMMMMMMMMMM",
                        sessionLogs = 100,
                    )
                }
            emit(Resource.Success(mockSessions.map { it.toSession() }))
        }
}
