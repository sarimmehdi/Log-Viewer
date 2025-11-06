package com.sarim.sidebar_dates_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Date(
    val id: Long,
    val dateHeading: String,
    val dateSessions: Int,
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Date) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}
