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
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.sarim.sidebar_domain.model.Date
import com.sarim.sidebar_domain.model.Session
import com.sarim.sidebar_presentation.component.SidebarHeaderComponent
import com.sarim.sidebar_presentation.component.SidebarListItemComponent
import com.sarim.sidebar_presentation.component.SidebarListItemComponentData
import com.sarim.utils.component.CustomScrollableListComponent
import com.sarim.utils.component.CustomScrollableListComponentData
import com.sarim.utils.component.HorizontalDividerComponent
import com.sarim.utils.component.SearchboxComponent
import com.sarim.utils.component.SearchboxComponentData
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import java.util.UUID

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
                .background(Color(0xFF03111B)),
    ) {
        SidebarHeaderComponent()
        HorizontalDividerComponent()
        SearchboxComponent(
            data =
                SearchboxComponentData(
                    placeholderText = "Search dates",
                    iconDescription = "Icon to search dates",
                    backgroundColor = Color(0xFF01070B),
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
                    backgroundColor = Color(0xFF01070B),
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

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun SidebarComponentPreview(
    @PreviewParameter(SidebarComponentDataParameterProvider::class) data: SidebarScreenData,
) {
    SidebarScreen(
        data = data,
        onEvent = {},
    )
}

class SidebarComponentDataParameterProvider : PreviewParameterProvider<SidebarScreenData> {
    override val values =
        sequenceOf(
            SidebarScreenData(
                dateObjects =
                    List(10) { index ->
                        val date =
                            Date(
                                id = UUID.randomUUID().toString(),
                                dateHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                                dateSessions = index,
                                selected = index % 2 == 0,
                            )
                        SidebarListItemComponentData.DateItem(
                            date = date,
                        )
                    }.toImmutableList(),
                sessionObjects =
                    List(10) { index ->
                        val session =
                            Session(
                                id = UUID.randomUUID().toString(),
                                sessionHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                                sessionLogs = index,
                                selected = index % 2 == 0,
                            )
                        SidebarListItemComponentData.SessionItem(
                            session = session,
                        )
                    }.toImmutableList(),
            ),
        )
}
