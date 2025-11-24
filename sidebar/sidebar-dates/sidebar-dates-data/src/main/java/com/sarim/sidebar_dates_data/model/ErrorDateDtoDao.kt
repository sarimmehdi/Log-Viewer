package com.sarim.sidebar_dates_data.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ErrorDateDtoDao : DateDtoDao {
    override fun insertAll(dates: List<DateDto>): Unit = error("DAO always fails")

    override fun getDateDtosAccordingToHeading(searchQuery: String): Flow<List<DateDto>> =
        flow {
            error("DAO always fails")
        }

    override fun getAll(): Flow<List<DateDto>> = flow { error("DAO always fails") }
}
