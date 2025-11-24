package com.sarim.sidebar_sessions_data.model

import kotlinx.coroutines.flow.flow

class ErrorSessionDtoDao : SessionDtoDao {
    override fun insertAll(sessions: List<SessionDto>): Unit = error("DAO always fails")

    override fun getSessionDtosAccordingToHeading(
        searchQuery: String,
        dateId: Long,
    ) = flow<List<SessionDto>> { error("DAO always fails") }

    override fun getAllSessionsForDateId(dateId: Long) = flow<List<SessionDto>> { error("DAO always fails") }

    override fun getAll() = flow<List<SessionDto>> { error("DAO always fails") }
}
