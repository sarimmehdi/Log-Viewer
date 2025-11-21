package com.sarim.maincontent_presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import com.sarim.maincontent_domain.model.LogMessage
import com.sarim.maincontent_domain.model.LogMessage.LogType
import kotlinx.collections.immutable.toImmutableList

const val LOG_OBJECTS = 100

@Preview(
    device = PIXEL_TABLET,
)
@Composable
fun MainContentComponentPreview() {
    val baseLogItems =
        listOf(
            LogMessage(
                logMessageId = 0,
                sessionId = 0,
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                functionName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                lineNumber = 9999,
                logType = LogType.ERROR,
            ),
            LogMessage(
                logMessageId = 1,
                sessionId = 0,
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                functionName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                lineNumber = 9999,
                logType = LogType.DEBUG,
            ),
            LogMessage(
                logMessageId = 2,
                sessionId = 0,
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                functionName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                lineNumber = 9999,
                logType = LogType.INFO,
            ),
            LogMessage(
                logMessageId = 3,
                sessionId = 0,
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                functionName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                lineNumber = 9999,
                logType = LogType.WARN,
            ),
            LogMessage(
                logMessageId = 4,
                sessionId = 0,
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                functionName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                lineNumber = 9999,
                logType = LogType.CRITICAL,
            ),
        )
    MainContentScreen(
        data =
            MainContentScreenData(
                logs = List(LOG_OBJECTS) { index -> baseLogItems[index % baseLogItems.size] }.toImmutableList(),
            ),
    )
}
