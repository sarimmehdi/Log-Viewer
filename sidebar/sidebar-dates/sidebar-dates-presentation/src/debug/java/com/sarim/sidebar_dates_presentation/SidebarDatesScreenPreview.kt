package com.sarim.sidebar_dates_presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sarim.sidebar_dates_domain.model.Date
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

private const val LIST_ITEM_COUNT = 10

internal class SidebarDatesScreenDataParameterProvider : PreviewParameterProvider<SidebarDatesScreenData> {
    override val values =
        sequenceOf(
            SidebarDatesScreenData(
                dates =
                    List(LIST_ITEM_COUNT) { index ->
                        Date(
                            dateId = index.toLong(),
                            dateHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                            dateSessions = index,
                        )
                    }.toImmutableList(),
                selectedDate =
                    Date(
                        dateId = 0,
                        dateHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                        dateSessions = 10,
                    ),
            ),
        )
}
