package com.sarim.header_presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun CustomRadioButtonComponentPreview(
    @PreviewParameter(CustomRadioButtonComponentDataParameterProvider::class) data: CustomRadioButtonComponentData,
) {
    CustomRadioButtonComponent(
        data = data,
    )
}

internal class CustomRadioButtonComponentDataParameterProvider : PreviewParameterProvider<CustomRadioButtonComponentData> {
    override val values =
        sequenceOf(
            CustomRadioButtonComponentData(
                on = true,
            ),
            CustomRadioButtonComponentData(
                on = false,
            ),
        )
}
