package com.sarim.logviewer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainContentComponent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(855.dp)
            .height(800.dp)
            .background(Color(0xFF01070B))
    )
}

@Preview(
    device = PIXEL_TABLET
)
@Composable
fun MainContentComponentPreview() {
    MainContentComponent()
}