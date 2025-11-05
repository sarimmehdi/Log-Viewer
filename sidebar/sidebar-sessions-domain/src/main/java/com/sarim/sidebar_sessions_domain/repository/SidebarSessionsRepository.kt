package com.sarim.sidebar_sessions_domain.repository

import com.sarim.sidebar_sessions_domain.model.Session
import com.sarim.utils.ui.Resource
import kotlinx.coroutines.flow.Flow

interface SidebarSessionsRepository {
    fun getSessions(dateId: String): Flow<Resource<List<Session>>>
}
