package com.sarim.sidebar_sessions_data.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SessionDto::class, SessionDtoFts::class],
    version = 1,
)
abstract class SessionDtoDatabase : RoomDatabase() {
    abstract val dao: SessionDtoDao

    companion object {
        const val DATABASE_NAME = "sessions.db"
    }
}
