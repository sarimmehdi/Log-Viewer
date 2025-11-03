package com.sarim.sidebar_domain.model

import android.os.Parcelable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.parcelize.Parcelize

@Parcelize
data class Date(
    val id: String,
    val dateHeading: String,
    val dateSessions: Int,
    val selected: Boolean,
) : Parcelable

fun ImmutableList<Date>.select(target: Date?): ImmutableList<Date> =
    map { item ->
        if (target != null && item.id == target.id) {
            item.copy(selected = true)
        } else {
            item.copy(selected = false)
        }
    }.toImmutableList()

fun ImmutableList<Date>.getSelected(): Date? = firstOrNull { it.selected }
