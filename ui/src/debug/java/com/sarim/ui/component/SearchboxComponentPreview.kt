package com.sarim.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
@Preview(
    device = PIXEL_TABLET,
)
private fun SearchboxComponentPreview(
    @PreviewParameter(SearchboxComponentDataParameterProvider::class) data: SearchboxComponentData,
) {
    SearchboxComponent(
        data = data,
    )
}

private class SearchboxComponentDataParameterProvider : PreviewParameterProvider<SearchboxComponentData> {
    override val values =
        sequenceOf(
            SearchboxComponentData(
                placeholderText = "Search dates",
                iconDescription = "Icon to search dates",
                backgroundColor = Color(0xFF01070B),
                onValueChange = {},
            ),
            SearchboxComponentData(
                placeholderText = "Search sessions",
                iconDescription = "Icon to search sessions",
                backgroundColor = Color(0xFF01070B),
                onValueChange = {},
            ),
            SearchboxComponentData(
                placeholderText = "Search messages",
                iconDescription = "Icon to search messages",
                backgroundColor = Color(0xFF03111B),
                onValueChange = {},
            ),
        )
}
