package com.sarim.sidebar_presentation

import com.sarim.sidebar_domain.model.Date
import com.sarim.sidebar_domain.model.Session

sealed interface SidebarScreenToViewModelEvents {
    data class FilterDates(
        val dateName: String,
    ) : SidebarScreenToViewModelEvents

    data class FilterSessions(
        val sessionName: String,
    ) : SidebarScreenToViewModelEvents

    data class SelectDate(
        val date: Date,
    ) : SidebarScreenToViewModelEvents

    data class SelectSession(
        val session: Session,
    ) : SidebarScreenToViewModelEvents
}
