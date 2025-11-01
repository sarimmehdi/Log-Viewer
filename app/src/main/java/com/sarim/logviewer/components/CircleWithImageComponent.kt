package com.sarim.logviewer.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.sarim.logviewer.R

@Composable
fun CircleWithImageComponent(
    modifier: Modifier = Modifier,
    data: CircleWithImageComponentData = CircleWithImageComponentData(),
) {
    Box(
        modifier =
            modifier
                .alpha(data.alpha)
                .size(30.dp)
                .background(Color(0xFF03111B), shape = CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(id = data.imageResource),
            contentDescription = data.imageResourceDescription,
            tint = Color.Unspecified,
            modifier =
                Modifier
                    .alpha(data.alpha)
                    .width(13.57.dp)
                    .height(13.89.dp)
                    .rotate(data.imageRotation),
        )
    }
}

data class CircleWithImageComponentData(
    @param:DrawableRes val imageResource: Int = R.drawable.double_arrows,
    val imageResourceDescription: String = "",
    val imageRotation: Float = 0.0f,
    val alpha: Float = 1.0f,
)

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

class CircleWithImageComponentDataParameterProvider : PreviewParameterProvider<CircleWithImageComponentData> {
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
        )
}
