package com.sarim.footer_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Footer(
    val currentPageNum: Int,
    val maxResultsPerPage: Int,
) : Parcelable
