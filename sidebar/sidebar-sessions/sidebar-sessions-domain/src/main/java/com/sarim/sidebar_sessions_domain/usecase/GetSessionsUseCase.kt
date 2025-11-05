package com.sarim.sidebar_sessions_domain.usecase

import com.sarim.sidebar_sessions_domain.model.Session
import com.sarim.sidebar_sessions_domain.repository.SidebarSessionsRepository

class GetSessionsUseCase(
    val repository: SidebarSessionsRepository,
) {
    operator fun invoke(dateId: String) = repository.getSessions(dateId)

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
