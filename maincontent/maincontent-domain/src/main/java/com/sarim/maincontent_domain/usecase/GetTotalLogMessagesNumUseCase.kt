package com.sarim.maincontent_domain.usecase

import com.sarim.maincontent_domain.repository.LogMessageRepository

class GetTotalLogMessagesNumUseCase(
    val repository: LogMessageRepository,
) {
    operator fun invoke() = repository.getTotalLogMessagesNum()
}
