package com.sarim.footer_presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sarim.footer_presentation.R

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun CircleWithImageComponentPreview(
    @PreviewParameter(CircleWithImageComponentDataParameterProvider::class) data: CircleWithImageComponentData,
) {
    CircleWithImageComponent(
        data = data,
    )
}

internal class CircleWithImageComponentDataParameterProvider : PreviewParameterProvider<CircleWithImageComponentData> {
    override val values =
        sequenceOf(
            CircleWithImageComponentData(
                imageResource = R.drawable.double_arrows,
                imageResourceDescription = "Click on this icon to go to the first page of log messages",
                imageRotation = 180.0f,
                alpha = 0.8f,
            ),
            CircleWithImageComponentData(
                imageResource = R.drawable.single_arrow,
                imageResourceDescription = "Click on this icon to go to the previous page of log messages",
                imageRotation = 180.0f,
                alpha = 0.8f,
            ),
            CircleWithImageComponentData(
                imageResource = R.drawable.double_arrows,
                imageResourceDescription = "Click on this icon to go to the first page of log messages",
                imageRotation = 180.0f,
                alpha = 1.0f,
            ),
            CircleWithImageComponentData(
                imageResource = R.drawable.single_arrow,
                imageResourceDescription = "Click on this icon to go to the previous page of log messages",
                imageRotation = 180.0f,
                alpha = 1.0f,
            ),
            CircleWithImageComponentData(
                imageResource = R.drawable.single_arrow,
                imageResourceDescription = "Click on this icon to go to the next page of log messages",
                imageRotation = 0.0f,
                alpha = 1.0f,
            ),
            CircleWithImageComponentData(
                imageResource = R.drawable.double_arrows,
                imageResourceDescription = "Click on this icon to go to the last page of log messages",
                imageRotation = 0.0f,
                alpha = 1.0f,
            ),
            CircleWithImageComponentData(
                imageResource = R.drawable.single_arrow,
                imageResourceDescription = "Click on this icon to go to the next page of log messages",
                imageRotation = 0.0f,
                alpha = 0.8f,
            ),
            CircleWithImageComponentData(
                imageResource = R.drawable.double_arrows,
                imageResourceDescription = "Click on this icon to go to the last page of log messages",
                imageRotation = 0.0f,
                alpha = 0.8f,
            ),
        )
}
