package com.sarim.sidebar_dates_presentation.debug

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sarim.sidebar_dates_domain.model.Date
import com.sarim.sidebar_dates_presentation.SidebarDatesScreen
import com.sarim.sidebar_dates_presentation.SidebarDatesScreenData
import kotlinx.collections.immutable.toImmutableList

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
                            id = index.toLong(),
                            dateHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                            dateSessions = index,
                        )
                    }.toImmutableList(),
                selectedDate =
                    Date(
                        id = 0,
                        dateHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                        dateSessions = 0,
                    ),
            ),
        )
}
