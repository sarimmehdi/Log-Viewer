package com.sarim.sidebar_presentation.debug

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sarim.sidebar_domain.model.Date
import com.sarim.sidebar_domain.model.Session
import com.sarim.sidebar_presentation.SidebarScreen
import com.sarim.sidebar_presentation.SidebarScreenData
import com.sarim.sidebar_presentation.component.SidebarListItemComponentData
import kotlinx.collections.immutable.toImmutableList
import java.util.UUID

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
