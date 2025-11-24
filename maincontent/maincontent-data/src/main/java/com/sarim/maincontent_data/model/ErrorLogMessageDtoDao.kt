package com.sarim.maincontent_data.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ErrorLogMessageDtoDao : LogMessageDtoDao {
    override fun insertAll(logMessages: List<LogMessageDto>): Unit = error("DAO always fails")

    override fun getLogMessageDtosAccordingToMessage(
        searchQuery: String,
        sessionId: Long,
    ): Flow<List<LogMessageDto>> = flow { error("DAO always fails") }

    override suspend fun getLogsForPage(
        sessionId: Long,
        pageSize: Int,
        offset: Int,
    ): List<LogMessageDto> = error("DAO always fails")

    override fun getAllLogMessagesForSessionId(sessionId: Long): Flow<List<LogMessageDto>> =
        flow {
            error("DAO always fails")
        }

    override fun getTotalLogCount(sessionId: Long): Flow<Int> = flow { error("DAO always fails") }
}
