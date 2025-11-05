package com.sarim.sidebar_dates_domain.usecase

import com.sarim.sidebar_dates_domain.model.Date
import com.sarim.sidebar_dates_domain.repository.SidebarDatesRepository

class GetDatesUseCase(
    val repository: SidebarDatesRepository,
) {
    operator fun invoke() = repository.dates

    fun filterDates(
        session: String,
        sessions: List<Date>,
    ) = if (session.isBlank()) {
        sessions
    } else {
        val query = session.lowercase()
        sessions.filter { it.dateHeading.lowercase().contains(query) }
    }
}
