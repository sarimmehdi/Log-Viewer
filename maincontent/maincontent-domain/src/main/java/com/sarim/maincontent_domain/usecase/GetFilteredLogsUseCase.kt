package com.sarim.maincontent_domain.usecase

import com.sarim.maincontent_domain.repository.LogMessageRepository

class GetFilteredLogsUseCase(
    val repository: LogMessageRepository,
) {
    suspend operator fun invoke(searchFilter: String) =
        repository
            .getLogsAccordingToSearchFilter(searchFilter)
}
