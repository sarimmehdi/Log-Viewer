package com.sarim.sidebar_sessions_domain.repository

import com.sarim.sidebar_sessions_domain.model.Session
import com.sarim.utils.ui.Resource
import kotlinx.coroutines.flow.Flow

interface SidebarSessionsRepository {
    fun getSessions(dateId: Long): Flow<Resource<List<Session>>>

    suspend fun selectSession(session: Session): Resource<Boolean>

    fun getSelectedSession(): Flow<Resource<Session>>

    suspend fun getSessionsAccordingToSearchFilterForDate(
        searchFilter: String,
        dateId: Long,
    ): Flow<Resource<List<Session>>>
}
