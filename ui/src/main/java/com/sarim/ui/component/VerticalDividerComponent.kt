package com.sarim.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun VerticalDividerComponent(modifier: Modifier = Modifier) {
    Box(
        modifier
            .width(1.dp)
            .background(Color.White.copy(alpha = 0.1f)),
    )
}
