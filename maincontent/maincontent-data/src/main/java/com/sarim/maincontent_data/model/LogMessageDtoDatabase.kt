package com.sarim.maincontent_data.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LogMessageDto::class, LogMessageDtoFts::class],
    version = 1,
)
abstract class LogMessageDtoDatabase : RoomDatabase() {
    abstract val dao: LogMessageDtoDao

    companion object {
        const val DATABASE_NAME = "log_messages.db"
    }
}
