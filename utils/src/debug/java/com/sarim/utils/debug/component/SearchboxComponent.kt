package com.sarim.utils.debug.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sarim.utils.component.SearchboxComponent
import com.sarim.utils.component.SearchboxComponentData

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun SearchboxComponentPreview(
    @PreviewParameter(SearchboxComponentDataParameterProvider::class) data: SearchboxComponentData,
) {
    SearchboxComponent(
        data = data,
    )
}

class SearchboxComponentDataParameterProvider : PreviewParameterProvider<SearchboxComponentData> {
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
