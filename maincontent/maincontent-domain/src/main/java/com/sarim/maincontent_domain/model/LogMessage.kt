package com.sarim.maincontent_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

enum class LogType(
    val logTypeName: String,
) {
    ERROR("Error"),
    DEBUG("Debug"),
    INFO("Info"),
    WARN("Warn"),
    CRITICAL("Critical"),
}

@Parcelize
data class LogMessage(
    val logMessageId: Long,
    val sessionId: Long,
    val message: String,
    val className: String,
    val functionName: String,
    val lineNumber: Int,
    val logType: LogType,
) : Parcelable
