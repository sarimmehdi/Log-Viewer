package com.sarim.maincontent_presentation

import com.sarim.footer_presentation.FooterScreenToViewModelEvents
import com.sarim.header_presentation.HeaderScreenToViewModelEvents

sealed interface MainContentScreenToViewModelEvents {
    data class FooterEvent(
        val event: FooterScreenToViewModelEvents,
    ) : MainContentScreenToViewModelEvents

    data class HeaderEvent(
        val event: HeaderScreenToViewModelEvents,
    ) : MainContentScreenToViewModelEvents
}
