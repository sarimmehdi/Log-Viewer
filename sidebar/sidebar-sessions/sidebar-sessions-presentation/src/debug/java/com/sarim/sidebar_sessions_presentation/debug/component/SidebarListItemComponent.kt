package com.sarim.sidebar_sessions_presentation.debug.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sarim.utils.component.SidebarListItemComponent
import com.sarim.utils.component.SidebarListItemComponentData

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun SidebarListItemComponentPreview(
    @PreviewParameter(SidebarListItemComponentDataParameterProvider::class) data: SidebarListItemComponentData,
) {
    SidebarListItemComponent(
        data = data,
        onClick = {},
    )
}

class SidebarListItemComponentDataParameterProvider : PreviewParameterProvider<SidebarListItemComponentData> {
    override val values =
        sequenceOf(
            SidebarListItemComponentData(
                heading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                subHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                selected = true,
            ),
            SidebarListItemComponentData(
                heading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                subHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                selected = false,
            ),
        )
}
