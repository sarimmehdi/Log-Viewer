package com.sarim.footer_presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun FooterScreenPreview(
    @PreviewParameter(FooterComponentDataParameterProvider::class) data: FooterScreenData,
) {
    FooterScreen(
        data = data,
        onEvent = {},
    )
}

internal class FooterComponentDataParameterProvider : PreviewParameterProvider<FooterScreenData> {
    override val values =
        sequenceOf(
            FooterScreenData(
                currentPageNumber = 9,
                numberFirstLogOnPage = 1,
                totalLogs = 100,
                numberLastLogOnPage = 10,
                totalPages = 10,
                canGoToNextPage = true,
                canGoToPreviousPage = true,
            ),
            FooterScreenData(
                currentPageNumber = 99,
                numberFirstLogOnPage = 1,
                totalLogs = 100,
                numberLastLogOnPage = 10,
                totalPages = 10,
                canGoToNextPage = false,
                canGoToPreviousPage = true,
            ),
            FooterScreenData(
                currentPageNumber = 999,
                numberFirstLogOnPage = 1,
                totalLogs = 100,
                numberLastLogOnPage = 10,
                totalPages = 10,
                canGoToNextPage = true,
                canGoToPreviousPage = false,
            ),
            FooterScreenData(
                currentPageNumber = 9999,
                numberFirstLogOnPage = 1,
                totalLogs = 100,
                numberLastLogOnPage = 10,
                totalPages = 10,
                canGoToNextPage = false,
                canGoToPreviousPage = false,
            ),
        )
}
