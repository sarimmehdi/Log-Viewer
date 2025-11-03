package com.sarim.sidebar_presentation.debug.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sarim.sidebar_domain.model.Date
import com.sarim.sidebar_domain.model.Session
import com.sarim.sidebar_presentation.component.SidebarListItemComponent
import com.sarim.sidebar_presentation.component.SidebarListItemComponentData
import java.util.UUID

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun SidebarListItemComponentPreview(
    @PreviewParameter(SidebarListItemComponentDataParameterProvider::class) data: SidebarListItemComponentData,
) {
    SidebarListItemComponent(
        data = data,
        onEvent = {},
    )
}

class SidebarListItemComponentDataParameterProvider : PreviewParameterProvider<SidebarListItemComponentData> {
    override val values =
        sequenceOf(
            SidebarListItemComponentData.DateItem(
                date =
                    Date(
                        id = UUID.randomUUID().toString(),
                        dateHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                        dateSessions = 9999,
                        selected = true,
                    ),
            ),
            SidebarListItemComponentData.SessionItem(
                session =
                    Session(
                        id = UUID.randomUUID().toString(),
                        sessionHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                        sessionLogs = 9999,
                        selected = true,
                    ),
            ),
        )
}
