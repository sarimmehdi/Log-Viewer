package com.sarim.footer_domain.usecase

enum class ChangeType {
    INCREASE,
    DECREASE,
}

class ChangeCurrentPageNumUseCase {
    operator fun invoke(
        changeType: ChangeType,
        currPageNum: Int,
        totalPages: Int,
    ) = when (changeType) {
        ChangeType.INCREASE -> if (currPageNum < totalPages) currPageNum + 1 else totalPages
        ChangeType.DECREASE -> if (currPageNum > 1) currPageNum - 1 else 1
    }

    operator fun invoke(
        currPageNum: Int,
        totalPages: Int,
    ) = currPageNum.coerceIn(1, totalPages)
}
