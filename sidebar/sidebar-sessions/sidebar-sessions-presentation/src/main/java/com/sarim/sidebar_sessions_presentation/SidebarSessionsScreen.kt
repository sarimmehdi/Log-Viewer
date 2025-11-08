package com.sarim.sidebar_sessions_presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sarim.sidebar_sessions_domain.model.Session
import com.sarim.utils.component.CustomScrollableListComponent
import com.sarim.utils.component.CustomScrollableListComponentData
import com.sarim.utils.component.HorizontalDividerComponent
import com.sarim.utils.component.SearchboxComponent
import com.sarim.utils.component.SearchboxComponentData
import com.sarim.utils.component.SidebarListItemComponent
import com.sarim.utils.component.SidebarListItemComponentData
import kotlinx.collections.immutable.ImmutableList

const val SIDEBAR_SCREEN_BACKGROUND_COLOR = 0xFF03111B
const val SIDEBAR_SCREEN_SEARCH_BOX_BACKGROUND_COLOR = 0xFF01070B

@Composable
fun SidebarSessionsScreen(
    data: SidebarSessionsScreenData,
    onEvent: (SidebarSessionsScreenToViewModelEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .width(425.dp)
                .background(Color(SIDEBAR_SCREEN_BACKGROUND_COLOR)),
    ) {
        SearchboxComponent(
            data =
                SearchboxComponentData(
                    placeholderText = stringResource(R.string.search_sessions),
                    iconDescription = stringResource(R.string.search_sessions_icon_desc),
                    backgroundColor = Color(SIDEBAR_SCREEN_SEARCH_BOX_BACKGROUND_COLOR),
                    onValueChange = {
                        if (it.isBlank()) {
                            onEvent(
                                SidebarSessionsScreenToViewModelEvents.GetAllSessionForSelectedDate,
                            )
                        } else {
                            onEvent(
                                SidebarSessionsScreenToViewModelEvents.FilterSessions(
                                    sessionName = it,
                                ),
                            )
                        }
                    },
                ),
            modifier =
                Modifier
                    .padding(top = 18.dp, start = 33.dp),
        )
        HorizontalDividerComponent(
            modifier =
                Modifier
                    .padding(top = 18.dp),
        )
        Spacer(
            modifier = Modifier.size(22.dp),
        )
        CustomScrollableListComponent(
            customScrollableListComponentData =
                CustomScrollableListComponentData(
                    contentHeight = 234.dp,
                ) {
                    items(data.sessions.size) { index ->
                        val session = data.sessions[index]
                        val selectedSession = data.selectedSession
                        SidebarListItemComponent(
                            data =
                                SidebarListItemComponentData(
                                    heading = session.sessionHeading,
                                    subHeading =
                                        pluralStringResource(
                                            R.plurals.logs,
                                            session.sessionLogs,
                                            session.sessionLogs,
                                        ),
                                    selected = session == selectedSession,
                                ),
                            onClick = {
                                onEvent(
                                    SidebarSessionsScreenToViewModelEvents.SelectSession(session),
                                )
                            },
                        )
                    }
                },
        )
    }
}

data class SidebarSessionsScreenData(
    val sessions: ImmutableList<Session>,
    val selectedSession: Session?,
)
