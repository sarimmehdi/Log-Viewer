package com.sarim.logviewer.components

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
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Composable
fun SidebarComponent(
    modifier: Modifier = Modifier,
    data: SidebarComponentData = SidebarComponentData(),
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
                        )
                    }
                },
        )
    }
}

data class SidebarComponentData(
    val dateObjects: ImmutableList<SidebarListItemComponentData> = persistentListOf(),
    val sessionObjects: ImmutableList<SidebarListItemComponentData> = persistentListOf(),
)

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun SidebarComponentPreview(
    @PreviewParameter(SidebarComponentDataParameterProvider::class) data: SidebarComponentData,
) {
    SidebarComponent(
        data = data,
    )
}

class SidebarComponentDataParameterProvider : PreviewParameterProvider<SidebarComponentData> {
    override val values =
        sequenceOf(
            SidebarComponentData(
                dateObjects =
                    List(10) { index ->
                        SidebarListItemComponentData(
                            heading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                            subHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                            selected = index % 2 == 0,
                        )
                    }.toImmutableList(),
                sessionObjects =
                    List(10) { index ->
                        SidebarListItemComponentData(
                            heading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                            subHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                            selected = index % 2 == 0,
                        )
                    }.toImmutableList(),
            ),
        )
}
