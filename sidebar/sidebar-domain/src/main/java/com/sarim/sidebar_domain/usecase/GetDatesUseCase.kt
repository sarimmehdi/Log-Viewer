package com.sarim.sidebar_domain.usecase

import com.sarim.sidebar_domain.model.Date
import com.sarim.sidebar_domain.repository.SidebarRepository

class GetDatesUseCase(
    val repository: SidebarRepository,
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
