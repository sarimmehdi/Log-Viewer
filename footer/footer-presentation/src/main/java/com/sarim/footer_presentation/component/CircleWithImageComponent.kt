package com.sarim.footer_presentation.component

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
import androidx.compose.ui.unit.dp

const val CIRCLE_WITH_IMAGE_COMPONENT_COLOR = 0xFF03111B

@Composable
fun CircleWithImageComponent(
    modifier: Modifier = Modifier,
    data: CircleWithImageComponentData,
) {
    Box(
        modifier =
            modifier
                .alpha(data.alpha)
                .size(30.dp)
                .background(Color(CIRCLE_WITH_IMAGE_COMPONENT_COLOR), shape = CircleShape),
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
    @param:DrawableRes val imageResource: Int,
    val imageResourceDescription: String,
    val imageRotation: Float,
    val alpha: Float,
)
