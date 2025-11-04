package com.sarim.maincontent_presentation

import android.os.Parcelable
import com.sarim.maincontent_domain.model.LogMessage
import com.sarim.maincontent_domain.model.LogType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainContentScreenState(
    val logMessages: ImmutableList<LogMessage> = persistentListOf(),
    val selectedLogMessages: ImmutableList<LogMessage> = persistentListOf(),
    val logMessageFilter: String = "",
    val classNamesToShow: ImmutableList<String> = persistentListOf(),
    val functionNamesToShow: ImmutableList<String> = persistentListOf(),
    val levelsToShow: ImmutableList<LogType> = persistentListOf(),
) : Parcelable
