package com.sarim.header_presentation

sealed interface HeaderScreenToViewModelEvents {
    data class FilterLogs(
        val text: String,
    ) : HeaderScreenToViewModelEvents

    data class CangeDropDownType(
        val dropDownType: DropDownType,
    ) : HeaderScreenToViewModelEvents
}
