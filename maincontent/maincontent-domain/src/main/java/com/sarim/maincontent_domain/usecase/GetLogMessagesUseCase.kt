package com.sarim.maincontent_domain.usecase

import com.sarim.maincontent_domain.repository.LogMessageRepository

class GetLogMessagesUseCase(
    val repository: LogMessageRepository,
) {
    operator fun invoke(pageNumber: Int) = repository.getLogMessages(pageNumber)
}
