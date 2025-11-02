package com.sarim.sidebar_data.model

import com.sarim.sidebar_domain.model.Session

data class SessionDto(
    val id: String,
    val sessionHeading: String,
    val sessionLogs: Int,
)

fun SessionDto.toSession() =
    Session(
        id = id,
        sessionHeading = sessionHeading,
        sessionLogs = sessionLogs,
        selected = false,
    )
