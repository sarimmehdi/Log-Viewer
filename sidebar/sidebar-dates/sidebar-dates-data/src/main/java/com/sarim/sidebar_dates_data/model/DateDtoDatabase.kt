package com.sarim.sidebar_dates_data.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DateDto::class, DateDtoFts::class],
    version = 1,
)
abstract class DateDtoDatabase : RoomDatabase() {
    abstract val dao: DateDtoDao

    companion object {
        const val DATABASE_NAME = "dates.db"
    }
}
