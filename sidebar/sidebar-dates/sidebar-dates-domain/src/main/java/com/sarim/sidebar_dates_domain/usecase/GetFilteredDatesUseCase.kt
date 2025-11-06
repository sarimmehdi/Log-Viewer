package com.sarim.sidebar_dates_domain.usecase

import com.sarim.sidebar_dates_domain.repository.SidebarDatesRepository

class GetFilteredDatesUseCase(
    val repository: SidebarDatesRepository,
) {
    suspend operator fun invoke(searchFilter: String) =
        repository
            .getDatesAccordingToSearchFilter(searchFilter)
}
