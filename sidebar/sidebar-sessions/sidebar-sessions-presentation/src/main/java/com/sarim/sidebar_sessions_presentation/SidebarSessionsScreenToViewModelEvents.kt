package com.sarim.sidebar_sessions_presentation

import com.sarim.sidebar_sessions_domain.model.Session

sealed interface SidebarSessionsScreenToViewModelEvents {
    data class FilterSessions(
        val sessionName: String,
    ) : SidebarSessionsScreenToViewModelEvents

    data class SelectSession(
        val session: Session,
    ) : SidebarSessionsScreenToViewModelEvents
}
