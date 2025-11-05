package com.sarim.sidebar_dates_data.model

import com.sarim.sidebar_dates_domain.model.Date

data class DateDto(
    val id: String,
    val dateHeading: String,
    val dateSessions: Int,
)

fun DateDto.toDate() =
    Date(
        id = id,
        dateHeading = dateHeading,
        dateSessions = dateSessions,
        selected = false,
    )
