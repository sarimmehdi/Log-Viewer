package com.sarim.maincontent_presentation

import android.os.Parcelable
import com.sarim.maincontent_domain.model.LogMessage
import com.sarim.sidebar_sessions_domain.model.Session
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainContentScreenState(
    val logs: ImmutableList<LogMessage> = persistentListOf(),
    val selectedSession: Session? = null,
    val currentPageNum: Int = 1,
) : Parcelable
