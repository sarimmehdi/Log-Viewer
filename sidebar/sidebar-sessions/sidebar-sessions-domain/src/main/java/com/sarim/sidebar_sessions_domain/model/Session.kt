package com.sarim.sidebar_sessions_domain.model

import android.os.Parcelable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.parcelize.Parcelize

@Parcelize
data class Session(
    val id: String,
    val sessionHeading: String,
    val sessionLogs: Int,
    val selected: Boolean,
) : Parcelable

fun ImmutableList<Session>.select(target: Session?): ImmutableList<Session> =
    map { item ->
        if (target != null && item.id == target.id) {
            item.copy(selected = true)
        } else {
            item.copy(selected = false)
        }
    }.toImmutableList()
