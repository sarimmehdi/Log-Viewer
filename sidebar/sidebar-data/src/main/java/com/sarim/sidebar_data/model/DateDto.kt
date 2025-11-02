package com.sarim.sidebar_data.model

import com.sarim.sidebar_domain.model.Date

data class DateDto(
    val dateHeading: String,
    val dateSessions: Int,
)

fun DateDto.toDate() =
    Date(
        dateHeading = dateHeading,
        dateSessions = dateSessions,
    )
