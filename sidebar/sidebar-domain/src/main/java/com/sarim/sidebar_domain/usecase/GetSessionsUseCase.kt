package com.sarim.sidebar_domain.usecase

import com.sarim.sidebar_domain.model.Date
import com.sarim.sidebar_domain.model.Session
import com.sarim.sidebar_domain.repository.SidebarRepository

class GetSessionsUseCase(
    val repository: SidebarRepository,
) {
    operator fun invoke(date: Date?) = repository.getSessions(date)

    fun filterSessions(
        session: String,
        sessions: List<Session>,
    ) = if (session.isBlank()) {
        sessions
    } else {
        val query = session.lowercase()
        sessions.filter { it.sessionHeading.lowercase().contains(query) }
    }
}
