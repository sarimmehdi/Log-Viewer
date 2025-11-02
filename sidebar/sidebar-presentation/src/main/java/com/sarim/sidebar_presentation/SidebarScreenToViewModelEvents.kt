package com.sarim.sidebar_presentation

import com.sarim.sidebar_domain.model.Date

sealed interface SidebarScreenToViewModelEvents {
    data class GetSessions(
        val date: Date,
    ) : SidebarScreenToViewModelEvents

    data class FilterDates(
        val dateName: String,
    ) : SidebarScreenToViewModelEvents

    data class FilterSessions(
        val sessionName: String,
    ) : SidebarScreenToViewModelEvents
}
