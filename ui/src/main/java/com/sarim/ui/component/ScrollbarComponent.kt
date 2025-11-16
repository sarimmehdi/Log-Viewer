package com.sarim.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

const val SCROLL_BAR_BACKGROUND_COLOR = 0xFF004AD3

@Composable
fun ScrollbarComponent(modifier: Modifier = Modifier) {
    Box(
        modifier =
            modifier
                .width(17.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(SCROLL_BAR_BACKGROUND_COLOR)),
    )
}
