package com.sarim.header_presentation

import com.sarim.header_presentation.HeaderScreenState.DropDownType

sealed interface HeaderScreenToViewModelEvents {
    data class FilterLogs(
        val text: String,
    ) : HeaderScreenToViewModelEvents

    data class CangeDropDownType(
        val dropDownType: DropDownType,
    ) : HeaderScreenToViewModelEvents
}
