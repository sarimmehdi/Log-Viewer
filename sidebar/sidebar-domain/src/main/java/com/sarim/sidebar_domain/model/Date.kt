package com.sarim.sidebar_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Date(
    val dateHeading: String,
    val dateSessions: Int
) : Parcelable
