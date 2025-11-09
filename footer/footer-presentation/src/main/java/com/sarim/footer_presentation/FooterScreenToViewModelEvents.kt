package com.sarim.footer_presentation

import com.sarim.footer_domain.usecase.ChangeType

sealed interface FooterScreenToViewModelEvents {
    data class ChangeCurrentPageNumber(
        val pageNumber: Int,
    ) : FooterScreenToViewModelEvents

    data class ChangeCurrentPageNumberByOne(
        val changeType: ChangeType,
    ) : FooterScreenToViewModelEvents
}
