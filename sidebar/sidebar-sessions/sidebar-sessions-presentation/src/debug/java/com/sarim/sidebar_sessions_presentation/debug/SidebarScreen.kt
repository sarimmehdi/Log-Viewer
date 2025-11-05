package com.sarim.sidebar_sessions_presentation.debug

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sarim.sidebar_sessions_domain.model.Session
import com.sarim.sidebar_sessions_presentation.SidebarSessionsScreen
import com.sarim.sidebar_sessions_presentation.SidebarSessionsScreenData
import kotlinx.collections.immutable.toImmutableList
import java.util.UUID

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun SidebarSessionsScreenPreview(
    @PreviewParameter(SidebarSessionsDataParameterProvider::class) data: SidebarSessionsScreenData,
) {
    SidebarSessionsScreen(
        data = data,
        onEvent = {},
    )
}

class SidebarSessionsDataParameterProvider : PreviewParameterProvider<SidebarSessionsScreenData> {
    override val values =
        sequenceOf(
            SidebarSessionsScreenData(
                sessions =
                    List(10) { index ->
                        Session(
                            id = UUID.randomUUID().toString(),
                            sessionHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                            sessionLogs = index,
                            selected = index % 2 == 0,
                        )
                    }.toImmutableList(),
            ),
        )
}
