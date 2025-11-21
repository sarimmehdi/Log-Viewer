package com.sarim.footer_presentation

import com.sarim.footer_domain.usecase.ChangeCurrentPageNumUseCase

sealed interface FooterScreenToViewModelEvents {
    data class ChangeCurrentPageNumber(
        val pageNumber: Int,
    ) : FooterScreenToViewModelEvents

    data class ChangeCurrentPageNumberByOne(
        val changeType: ChangeCurrentPageNumUseCase.ChangeType,
    ) : FooterScreenToViewModelEvents
}
