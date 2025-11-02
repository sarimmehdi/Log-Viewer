package com.sarim.sidebar_domain.usecase

import com.sarim.sidebar_domain.model.Date
import com.sarim.sidebar_domain.repository.SidebarRepository

class GetDatesUseCase(
    val repository: SidebarRepository,
) {
    operator fun invoke() = repository.dates

    fun filterDates(
        date: String,
        dates: List<Date>,
    ) = if (date.isBlank()) {
        dates
    } else {
        dates.filter { it.dateHeading == date }
    }
}
