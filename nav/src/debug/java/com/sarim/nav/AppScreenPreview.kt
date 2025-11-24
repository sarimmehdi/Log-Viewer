package com.sarim.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import com.sarim.footer_presentation.FooterScreenData
import com.sarim.header_presentation.HeaderScreenData
import com.sarim.header_presentation.HeaderScreenState.DropDownType
import com.sarim.maincontent_domain.model.LogMessage
import com.sarim.maincontent_domain.model.LogMessage.LogType
import com.sarim.maincontent_presentation.LOG_OBJECTS
import com.sarim.maincontent_presentation.MainContentScreenData
import com.sarim.sidebar_dates_domain.model.Date
import com.sarim.sidebar_dates_presentation.SidebarDatesScreenData
import com.sarim.sidebar_sessions_domain.model.Session
import com.sarim.sidebar_sessions_presentation.SidebarSessionsScreenData
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

private const val LIST_ITEM_COUNT = 10

@Preview(
    device = PIXEL_TABLET,
)
@Composable
fun AppScreenPreview() {
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
    val data =
        AppScreenData(
            sidebarDatesScreenData =
                SidebarDatesScreenData(
                    dates =
                        List(LIST_ITEM_COUNT) { index ->
                            Date(
                                dateId = index.toLong(),
                                dateHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                                dateSessions = index,
                            )
                        }.toImmutableList(),
                    selectedDate =
                        Date(
                            dateId = 0,
                            dateHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                            dateSessions = 10,
                        ),
                ),
            sidebarSessionsScreenData =
                SidebarSessionsScreenData(
                    sessions =
                        List(LIST_ITEM_COUNT) { index ->
                            Session(
                                sessionId = index.toLong(),
                                dateId = 1,
                                sessionHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                                sessionLogs = index,
                            )
                        }.toImmutableList(),
                    selectedSession =
                        Session(
                            sessionId = 1,
                            dateId = 1,
                            sessionHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                            sessionLogs = 100,
                        ),
                ),
            headerScreenData =
                HeaderScreenData(
                    dropDownType = DropDownType.NONE,
                    classFilters = persistentListOf(),
                    functionFilters = persistentListOf(),
                    logTypeFilters = persistentListOf(),
                ),
            mainContentScreenData =
                MainContentScreenData(
                    logs = List(LOG_OBJECTS) { index -> baseLogItems[index % baseLogItems.size] }.toImmutableList(),
                ),
            footerScreenData =
                FooterScreenData(
                    currentPageNumber = 9,
                    numberFirstLogOnPage = 1,
                    totalLogs = 100,
                    numberLastLogOnPage = 10,
                    totalPages = 10,
                    canGoToNextPage = true,
                    canGoToPreviousPage = true,
                ),
        )

    val events =
        AppScreenOnEvent(
            sidebarDatesScreenToViewModelEvents = {},
            sidebarSessionsScreenToViewModelEvents = {},
            headerScreenToViewModelEvents = {},
            footerScreenToViewModelEvents = {},
        )

    AppScreen(
        data = data,
        onEvent = events,
        modifier = Modifier,
    )
}
