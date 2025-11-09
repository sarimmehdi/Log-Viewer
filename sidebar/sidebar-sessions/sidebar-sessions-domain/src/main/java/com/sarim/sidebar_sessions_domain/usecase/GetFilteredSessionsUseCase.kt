package com.sarim.sidebar_sessions_domain.usecase

import com.sarim.sidebar_sessions_domain.repository.SidebarSessionsRepository

class GetFilteredSessionsUseCase(
    val repository: SidebarSessionsRepository,
) {
    suspend operator fun invoke(searchFilter: String) =
        repository
            .getSessionsAccordingToSearchFilterForDate(searchFilter)
}
