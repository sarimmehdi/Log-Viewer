package com.sarim.footer_presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FooterScreenState(
    val currentPageNum: Int = 1,
    val maxResultsPerPage: Int = 10,
    val totalPages: Int = 1,
    val totalLogs: Int = 1,
    val numFirstLogOnCurrPage: Int = 1,
    val numLastLogOnCurrPage: Int = 1,
    val canGoToNextPage: Boolean = true,
    val canGoToPreviousPage: Boolean = true,
) : Parcelable
