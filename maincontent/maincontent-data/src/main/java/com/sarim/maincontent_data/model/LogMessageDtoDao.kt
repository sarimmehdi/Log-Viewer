package com.sarim.maincontent_data.model

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface LogMessageDtoDao {
    @Upsert
    fun insertAll(logMessages: List<LogMessageDto>)

    @Query(
        """
        SELECT * FROM logmessagedto
        JOIN logmessagedtofts ON logmessagedtofts.message == logmessagedto.message 
        WHERE logmessagedtofts.message MATCH :searchQuery AND logmessagedto.sessionId == :sessionId
    """,
    )
    fun getLogMessageDtosAccordingToMessage(
        searchQuery: String,
        sessionId: Long,
    ): Flow<List<LogMessageDto>>

    @Query("SELECT * FROM logmessagedto WHERE sessionId = :sessionId ORDER BY sessionId")
    fun getAllLogMessagesForSessionId(sessionId: Long): Flow<List<LogMessageDto>>
}
