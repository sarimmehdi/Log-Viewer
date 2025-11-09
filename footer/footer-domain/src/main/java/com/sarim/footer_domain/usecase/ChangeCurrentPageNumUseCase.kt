package com.sarim.footer_domain.usecase

import com.sarim.footer_domain.repository.FooterRepository

enum class ChangeType {
    INCREASE,
    DECREASE,
}

class ChangeCurrentPageNumUseCase(
    val repository: FooterRepository,
) {
    suspend operator fun invoke(
        changeType: ChangeType,
        currPageNum: Int,
        totalPages: Int,
    ) = repository.setCurrentPageNumber(
        currPageNum =
            when (changeType) {
                ChangeType.INCREASE -> if (currPageNum < totalPages) currPageNum + 1 else totalPages
                ChangeType.DECREASE -> if (currPageNum > 1) currPageNum - 1 else 1
            },
    )

    suspend operator fun invoke(
        currPageNum: Int,
        totalPages: Int,
    ) = repository.setCurrentPageNumber(
        currPageNum = currPageNum.coerceIn(1, totalPages),
    )
}
