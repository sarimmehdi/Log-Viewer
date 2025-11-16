package com.sarim.maincontent_presentation

import android.os.Parcelable
import com.sarim.footer_presentation.FooterScreenState
import com.sarim.header_presentation.HeaderScreenState
import com.sarim.maincontent_domain.model.LogMessage
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainContentScreenState(
    val logs: ImmutableList<LogMessage> = persistentListOf(),
    val footerScreenState: FooterScreenState = FooterScreenState(),
    val headerScreenState: HeaderScreenState = HeaderScreenState(),
) : Parcelable
