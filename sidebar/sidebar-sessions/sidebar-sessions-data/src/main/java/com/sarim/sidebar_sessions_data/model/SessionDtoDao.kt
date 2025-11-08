package com.sarim.sidebar_sessions_data.model

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface SessionDtoDao {
    @Upsert
    fun insertAll(sessions: List<SessionDto>)

    @Query(
        """
        SELECT * FROM sessiondto
        JOIN sessiondtofts ON sessiondtofts.sessionHeading == sessiondto.sessionHeading 
        WHERE sessiondtofts.sessionHeading MATCH :searchQuery AND sessiondto.dateId == :dateId
    """,
    )
    fun getSessionDtosAccordingToHeading(
        searchQuery: String,
        dateId: Long,
    ): Flow<List<SessionDto>>

    @Query("SELECT * FROM sessiondto WHERE dateId = :dateId ORDER BY sessionId")
    fun getAllSessionsForDateId(dateId: Long): Flow<List<SessionDto>>

    @Query("SELECT * FROM sessiondto ORDER BY sessionId")
    fun getAll(): Flow<List<SessionDto>>
}
