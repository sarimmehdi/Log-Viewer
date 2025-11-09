package com.sarim.footer_domain.usecase

class GetTotalPagesUseCase {
    operator fun invoke(
        totalLogs: Int,
        logsPerPage: Int,
    ) = if (logsPerPage > 0) (totalLogs + logsPerPage - 1) / logsPerPage else 0
}
