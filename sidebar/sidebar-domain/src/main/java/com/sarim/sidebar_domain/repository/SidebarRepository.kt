package com.sarim.sidebar_domain.repository

import com.sarim.sidebar_domain.model.Date
import com.sarim.sidebar_domain.model.Session
import com.sarim.utils.ui.Resource
import kotlinx.coroutines.flow.Flow

interface SidebarRepository {
    val dates: Flow<Resource<List<Date>>>

    fun getSessions(date: Date): Flow<Resource<List<Session>>>
}
