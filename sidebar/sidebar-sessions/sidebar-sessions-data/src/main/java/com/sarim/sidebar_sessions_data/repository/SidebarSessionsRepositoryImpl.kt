package com.sarim.sidebar_sessions_data.repository

import com.sarim.sidebar_sessions_data.model.SessionDto
import com.sarim.sidebar_sessions_data.model.toSession
import com.sarim.sidebar_sessions_domain.repository.SidebarSessionsRepository
import com.sarim.utils.ui.Resource
import kotlinx.coroutines.flow.flow
import java.util.UUID
import kotlin.collections.map

const val TOTAL_MOCK_ITEMS = 10

class SidebarSessionsRepositoryImpl : SidebarSessionsRepository {
    override fun getSessions(dateId: String) =
        flow {
            val mockSessions =
                List(TOTAL_MOCK_ITEMS) { index ->
                    SessionDto(
                        id = UUID.randomUUID().toString(),
                        sessionHeading = "Session ${index + 1}",
                        sessionLogs = 9999,
                    )
                }
            emit(Resource.Success(mockSessions.map { it.toSession() }))
        }
}
