package com.sarim.footer_presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun CurrentPageNumberComponentPreview(
    @PreviewParameter(CurrentPageNumberComponentDataParameterProvider::class) data: CurrentPageNumberComponentData,
) {
    CurrentPageNumberComponent(
        data = data,
        onDone = {},
    )
}

internal class CurrentPageNumberComponentDataParameterProvider : PreviewParameterProvider<CurrentPageNumberComponentData> {
    override val values =
        sequenceOf(
            CurrentPageNumberComponentData(
                currentPageNumber = 9,
            ),
            CurrentPageNumberComponentData(
                currentPageNumber = 99,
            ),
            CurrentPageNumberComponentData(
                currentPageNumber = 999,
            ),
            CurrentPageNumberComponentData(
                currentPageNumber = 9999,
            ),
        )
}
