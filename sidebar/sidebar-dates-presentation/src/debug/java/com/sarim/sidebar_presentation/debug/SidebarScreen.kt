package com.sarim.sidebar_presentation.debug

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sarim.sidebar_dates_domain.model.Date
import com.sarim.sidebar_dates_presentation.SidebarDatesScreen
import com.sarim.sidebar_dates_presentation.SidebarDatesScreenData
import kotlinx.collections.immutable.toImmutableList
import java.util.UUID

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun SidebarDatesScreenPreview(
    @PreviewParameter(SidebarDatesScreenDataParameterProvider::class) data: SidebarDatesScreenData,
) {
    SidebarDatesScreen(
        data = data,
        onEvent = {},
    )
}

class SidebarDatesScreenDataParameterProvider : PreviewParameterProvider<SidebarDatesScreenData> {
    override val values =
        sequenceOf(
            SidebarDatesScreenData(
                dates =
                    List(10) { index ->
                        Date(
                            id = UUID.randomUUID().toString(),
                            dateHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                            dateSessions = index,
                            selected = index % 2 == 0,
                        )
                    }.toImmutableList(),
            ),
        )
}
