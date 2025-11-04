package com.sarim.maincontent_data.model

import com.sarim.maincontent_domain.model.LogMessage
import com.sarim.maincontent_domain.model.LogType

data class LogMessageDto(
    val id: String,
    val message: String,
    val className: String,
    val functionName: String,
    val lineNumber: Int,
    val logType: String,
)

fun LogMessageDto.toLogMessage() =
    LogMessage(
        id = id,
        message = message,
        className = className,
        functionName = functionName,
        lineNumber = lineNumber,
        logType = LogType.fromString(logType),
    )
