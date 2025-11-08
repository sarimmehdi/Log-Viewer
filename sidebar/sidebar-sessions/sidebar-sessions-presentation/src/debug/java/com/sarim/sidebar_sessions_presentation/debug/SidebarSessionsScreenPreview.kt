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

@Composable
@Preview(
    device = PIXEL_TABLET,
)
private fun SidebarSessionsScreenPreview(
    @PreviewParameter(SidebarSessionsScreenDataParameterProvider::class) data: SidebarSessionsScreenData,
) {
    SidebarSessionsScreen(
        data = data,
        onEvent = {},
    )
}

private const val LIST_ITEM_COUNT = 10

private class SidebarSessionsScreenDataParameterProvider : PreviewParameterProvider<SidebarSessionsScreenData> {
    override val values =
        sequenceOf(
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
        )
}
