package com.sarim.sidebar_dates_presentation

import com.sarim.sidebar_dates_domain.model.Date

sealed interface SidebarDatesScreenToViewModelEvents {
    data class FilterDates(
        val dateName: String,
    ) : SidebarDatesScreenToViewModelEvents

    data class SelectDate(
        val date: Date,
    ) : SidebarDatesScreenToViewModelEvents

    data object GetAllDates : SidebarDatesScreenToViewModelEvents
}
