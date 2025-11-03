package com.sarim.sidebar_presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sarim.sidebar_presentation.component.SidebarHeaderComponent
import com.sarim.sidebar_presentation.component.SidebarListItemComponent
import com.sarim.sidebar_presentation.component.SidebarListItemComponentData
import com.sarim.utils.component.CustomScrollableListComponent
import com.sarim.utils.component.CustomScrollableListComponentData
import com.sarim.utils.component.HorizontalDividerComponent
import com.sarim.utils.component.SearchboxComponent
import com.sarim.utils.component.SearchboxComponentData
import kotlinx.collections.immutable.ImmutableList

const val SIDEBAR_SCREEN_BACKGROUND_COLOR = 0xFF03111B
const val SIDEBAR_SCREEN_SEARCH_BOX_BACKGROUND_COLOR = 0xFF01070B

@Composable
fun SidebarScreen(
    data: SidebarScreenData,
    onEvent: (SidebarScreenToViewModelEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .width(425.dp)
                .height(800.dp)
                .background(Color(SIDEBAR_SCREEN_BACKGROUND_COLOR)),
    ) {
        SidebarHeaderComponent()
        HorizontalDividerComponent()
        SearchboxComponent(
            data =
                SearchboxComponentData(
                    placeholderText = "Search dates",
                    iconDescription = "Icon to search dates",
                    backgroundColor = Color(SIDEBAR_SCREEN_SEARCH_BOX_BACKGROUND_COLOR),
                    onValueChange = {
                        onEvent(
                            SidebarScreenToViewModelEvents.FilterDates(
                                dateName = it,
                            ),
                        )
                    },
                ),
            modifier =
                Modifier
                    .padding(top = 18.dp, start = 33.dp),
        )
        HorizontalDividerComponent(
            modifier =
                Modifier
                    .padding(top = 20.dp),
        )
        Spacer(
            modifier = Modifier.size(9.dp),
        )
        CustomScrollableListComponent(
            customScrollableListComponentData =
                CustomScrollableListComponentData(
                    contentHeight = 233.dp,
                ) {
                    items(data.dateObjects.size) { index ->
                        SidebarListItemComponent(
                            data = data.dateObjects[index],
                            onEvent = onEvent,
                        )
                    }
                },
        )
        HorizontalDividerComponent(
            modifier =
                Modifier
                    .padding(top = 18.dp),
        )
        SearchboxComponent(
            data =
                SearchboxComponentData(
                    placeholderText = "Search sessions",
                    iconDescription = "Icon to search sessions",
                    backgroundColor = Color(SIDEBAR_SCREEN_SEARCH_BOX_BACKGROUND_COLOR),
                    onValueChange = {
                        onEvent(
                            SidebarScreenToViewModelEvents.FilterSessions(
                                sessionName = it,
                            ),
                        )
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
                    items(data.sessionObjects.size) { index ->
                        SidebarListItemComponent(
                            data = data.sessionObjects[index],
                            onEvent = onEvent,
                        )
                    }
                },
        )
    }
}

data class SidebarScreenData(
    val dateObjects: ImmutableList<SidebarListItemComponentData>,
    val sessionObjects: ImmutableList<SidebarListItemComponentData>,
)
