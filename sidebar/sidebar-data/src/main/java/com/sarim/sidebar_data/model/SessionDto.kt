package com.sarim.sidebar_data.model

import com.sarim.sidebar_domain.model.Session

data class SessionDto(
    val sessionHeading: String,
    val sessionLogs: Int,
)

fun SessionDto.toSession() =
    Session(
        sessionHeading = sessionHeading,
        sessionLogs = sessionLogs,
    )
