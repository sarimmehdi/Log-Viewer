package com.sarim.logviewer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ScrollbarComponent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(17.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF004AD3))
    )
}

@Preview
@Composable
fun ScrollbarComponentPreview() {
    ScrollbarComponent(
        modifier = Modifier.size(56.dp)
    )
}