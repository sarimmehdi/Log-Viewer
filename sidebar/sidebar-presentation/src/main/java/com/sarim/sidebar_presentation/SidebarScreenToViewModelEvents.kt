package com.sarim.sidebar_presentation

sealed interface SidebarScreenToViewModelEvents {
    data class FilterDates(
        val dateName: String
    ) : SidebarScreenToViewModelEvents

    data class FilterSessions(
        val sessionName: String
    ) : SidebarScreenToViewModelEvents
}