package com.sarim.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(
    device = PIXEL_TABLET,
)
@Composable
private fun ScrollbarComponentPreview() {
    ScrollbarComponent(
        modifier = Modifier.size(56.dp),
    )
}
