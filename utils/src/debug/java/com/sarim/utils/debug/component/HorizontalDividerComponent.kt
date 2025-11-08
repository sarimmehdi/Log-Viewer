package com.sarim.utils.debug.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import com.sarim.utils.component.HorizontalDividerComponent

@Preview(
    device = PIXEL_TABLET,
)
@Composable
private fun HorizontalDividerComponentPreview() {
    HorizontalDividerComponent()
}
