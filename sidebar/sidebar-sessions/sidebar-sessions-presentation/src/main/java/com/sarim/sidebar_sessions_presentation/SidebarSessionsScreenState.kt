package com.sarim.sidebar_sessions_presentation

import android.os.Parcelable
import com.sarim.sidebar_dates_domain.model.Date
import com.sarim.sidebar_sessions_domain.model.Session
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.parcelize.Parcelize

@Parcelize
data class SidebarSessionsScreenState(
    val sessions: ImmutableList<Session> = persistentListOf(),
    val selectedSession: Session? = null,
    val selectedDate: Date? = null,
) : Parcelable
