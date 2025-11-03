package com.sarim.sidebar_presentation

import android.os.Parcelable
import com.sarim.sidebar_domain.model.Date
import com.sarim.sidebar_domain.model.Session
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.parcelize.Parcelize

@Parcelize
data class SidebarScreenState(
    val dates: ImmutableList<Date> = persistentListOf(),
    val selectedDate: Date? = null,
    val sessions: ImmutableList<Session> = persistentListOf(),
    val selectedSession: Session? = null,
    val datesFilter: String = "",
    val sessionsFilter: String = "",
) : Parcelable
