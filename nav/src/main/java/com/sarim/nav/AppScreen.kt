package com.sarim.nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.sarim.footer_presentation.FooterScreen
import com.sarim.footer_presentation.FooterScreenData
import com.sarim.footer_presentation.FooterScreenToViewModelEvents
import com.sarim.header_presentation.HeaderScreen
import com.sarim.header_presentation.HeaderScreenData
import com.sarim.header_presentation.HeaderScreenToViewModelEvents
import com.sarim.maincontent_presentation.MainContentScreen
import com.sarim.maincontent_presentation.MainContentScreenData
import com.sarim.sidebar_dates_presentation.SidebarDatesScreen
import com.sarim.sidebar_dates_presentation.SidebarDatesScreenData
import com.sarim.sidebar_dates_presentation.SidebarDatesScreenToViewModelEvents
import com.sarim.sidebar_sessions_presentation.SidebarSessionsScreen
import com.sarim.sidebar_sessions_presentation.SidebarSessionsScreenData
import com.sarim.sidebar_sessions_presentation.SidebarSessionsScreenToViewModelEvents
import kotlinx.serialization.Serializable

private const val APP_SCREEN_BACKGROUND_COLOR = 0xFF01070B

@Composable
internal fun AppScreen(
    data: AppScreenData,
    onEvent: AppScreenOnEvent,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .background(Color(APP_SCREEN_BACKGROUND_COLOR)),
    ) {
        Column {
            SidebarDatesScreen(
                data = data.sidebarDatesScreenData,
                onEvent = onEvent.sidebarDatesScreenToViewModelEvents,
            )
            SidebarSessionsScreen(
                data = data.sidebarSessionsScreenData,
                onEvent = onEvent.sidebarSessionsScreenToViewModelEvents,
            )
        }
        Column {
            HeaderScreen(
                data = data.headerScreenData,
                onEvent = onEvent.headerScreenToViewModelEvents,
                modifier =
                    Modifier
                        .padding(
                            top = 24.dp,
                            start = 18.dp,
                            end = 18.dp,
                        ),
            )
            MainContentScreen(
                data = data.mainContentScreenData,
                modifier =
                    Modifier
                        .padding(
                            top = 27.dp,
                            start = 18.dp,
                            end = 18.dp,
                        ),
            )
            FooterScreen(
                data = data.footerScreenData,
                onEvent = onEvent.footerScreenToViewModelEvents,
                modifier =
                    Modifier
                        .padding(
                            top = 24.dp,
                            start = 18.dp,
                            end = 18.dp,
                        ),
            )
        }
    }
}

internal data class AppScreenData(
    val sidebarDatesScreenData: SidebarDatesScreenData,
    val sidebarSessionsScreenData: SidebarSessionsScreenData,
    val headerScreenData: HeaderScreenData,
    val mainContentScreenData: MainContentScreenData,
    val footerScreenData: FooterScreenData,
)

internal data class AppScreenOnEvent(
    val sidebarDatesScreenToViewModelEvents: (SidebarDatesScreenToViewModelEvents) -> Unit,
    val sidebarSessionsScreenToViewModelEvents: (SidebarSessionsScreenToViewModelEvents) -> Unit,
    val headerScreenToViewModelEvents: (HeaderScreenToViewModelEvents) -> Unit,
    val footerScreenToViewModelEvents: (FooterScreenToViewModelEvents) -> Unit,
)

@Serializable
internal data object AppScreen : NavKey
