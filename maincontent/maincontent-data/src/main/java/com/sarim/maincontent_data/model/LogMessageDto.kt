package com.sarim.maincontent_data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4
import androidx.room.PrimaryKey
import com.sarim.maincontent_domain.model.LogMessage
import com.sarim.maincontent_domain.model.LogType

const val LOG_MESSAGE_DTO_ID_COLUMN_NAME = "logMessageId"

@Entity
data class LogMessageDto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = LOG_MESSAGE_DTO_ID_COLUMN_NAME)
    val logMessageId: Long = 0,
    val sessionId: Long,
    val message: String,
    val className: String,
    val functionName: String,
    val lineNumber: Int,
    val logType: LogType,
) {
    companion object {
        fun LogMessageDto.toLogMessage() =
            LogMessage(
                logMessageId = logMessageId,
                sessionId = sessionId,
                message = message,
                className = className,
                functionName = functionName,
                lineNumber = lineNumber,
                logType = logType,
            )

        fun LogMessage.fromLogMessage() =
            LogMessageDto(
                logMessageId = logMessageId,
                sessionId = sessionId,
                message = message,
                className = className,
                functionName = functionName,
                lineNumber = lineNumber,
                logType = logType,
            )
    }
}

@Entity
@Fts4(contentEntity = LogMessageDto::class)
data class LogMessageDtoFts(
    @ColumnInfo(name = "rowid")
    @PrimaryKey(autoGenerate = true) val logMessageId: Int = 0,
    val message: String,
)
