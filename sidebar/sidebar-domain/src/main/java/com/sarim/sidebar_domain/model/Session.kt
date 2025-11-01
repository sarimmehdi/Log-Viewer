package com.sarim.sidebar_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Session(
    val sessionHeading: String,
    val sessionLogs: Int
) : Parcelable
