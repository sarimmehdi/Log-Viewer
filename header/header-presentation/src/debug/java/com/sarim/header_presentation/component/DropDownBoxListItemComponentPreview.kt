package com.sarim.header_presentation.component

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun DropDownBoxListItemComponentPreview(
    @PreviewParameter(DropDownBoxListItemComponentDataParameterProvider::class) data: DropDownBoxListItemComponentData,
) {
    DropDownBoxListItemComponent(
        data = data,
        modifier =
            Modifier
                .width(138.75.dp),
    )
}

class DropDownBoxListItemComponentDataParameterProvider : PreviewParameterProvider<DropDownBoxListItemComponentData> {
    override val values =
        sequenceOf(
            DropDownBoxListItemComponentData(
                itemName = "Class 1",
                itemFilterOn = true,
            ),
            DropDownBoxListItemComponentData(
                itemName = "Class 1",
                itemFilterOn = false,
            ),
        )
}
