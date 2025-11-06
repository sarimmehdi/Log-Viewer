package com.sarim.sidebar_dates_data.model

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DateDtoDao {
    @Upsert
    fun insertAll(dates: List<DateDto>)

    @Query(
        """
        SELECT * FROM datedto 
        JOIN datedtofts ON datedtofts.dateHeading == datedto.dateHeading 
        WHERE datedtofts.dateHeading MATCH :searchQuery
    """,
    )
    fun getDateDtosAccordingToHeading(searchQuery: String): Flow<List<DateDto>>

    @Query("SELECT * FROM dateDto ORDER BY id")
    fun getAll(): Flow<List<DateDto>>
}
