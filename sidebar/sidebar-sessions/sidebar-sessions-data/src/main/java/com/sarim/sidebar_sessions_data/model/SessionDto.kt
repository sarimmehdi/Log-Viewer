package com.sarim.sidebar_sessions_data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import com.sarim.sidebar_sessions_domain.model.Session
import kotlinx.serialization.Serializable

const val SESSION_DTO_ID_COLUMN_NAME = "sessionId"

@Serializable
@Entity
data class SessionDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SESSION_DTO_ID_COLUMN_NAME)
    val sessionId: Long = 0,
    val dateId: Long,
    val sessionHeading: String,
    val sessionLogs: Int,
) {
    companion object {
        val NO_SELECTED_SESSION_DTO =
            SessionDto(
                sessionId = -1,
                dateId = -1,
                sessionHeading = "",
                sessionLogs = -1,
            )
    }
}

fun createSessionDto(session: Session) =
    SessionDto(
        sessionId = session.sessionId,
        dateId = session.dateId,
        sessionHeading = session.sessionHeading,
        sessionLogs = session.sessionLogs,
    )

fun createSession(sessionDto: SessionDto) =
    Session(
        sessionId = sessionDto.sessionId,
        dateId = sessionDto.dateId,
        sessionHeading = sessionDto.sessionHeading,
        sessionLogs = sessionDto.sessionLogs,
    )

@Entity
@Fts4(contentEntity = SessionDto::class)
data class SessionDtoFts(
    @ColumnInfo(name = "rowid")
    @PrimaryKey(autoGenerate = true) val sessionId: Int = 0,
    val sessionHeading: String,
)
