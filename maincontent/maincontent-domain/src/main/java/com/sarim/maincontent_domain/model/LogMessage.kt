package com.sarim.maincontent_domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

enum class LogType {
    ERROR,
    DEBUG,
    INFO,
    WARN,
    CRITICAL,
    UNKNOWN,
    ;

    companion object {
        fun fromString(value: String): LogType =
            when (value.lowercase()) {
                "error" -> ERROR
                "debug" -> DEBUG
                "info" -> INFO
                "warning" -> WARN
                "critical" -> CRITICAL
                else -> UNKNOWN
            }
    }
}

@Parcelize
data class LogMessage(
    val id: String,
    val message: String,
    val className: String,
    val functionName: String,
    val lineNumber: Int,
    val logType: LogType,
) : Parcelable
