package com.sarim.sidebar_dates_presentation

import android.os.Parcelable
import com.sarim.sidebar_dates_domain.model.Date
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.parcelize.Parcelize

@Parcelize
data class SidebarDatesScreenState(
    val dates: ImmutableList<Date> = persistentListOf(),
    val selectedDate: Date? = null,
    val searchString: String = "",
) : Parcelable
