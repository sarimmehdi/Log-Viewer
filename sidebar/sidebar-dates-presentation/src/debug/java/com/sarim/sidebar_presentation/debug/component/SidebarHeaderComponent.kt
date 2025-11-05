package com.sarim.sidebar_presentation.debug.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import com.sarim.sidebar_dates_presentation.component.SidebarHeaderComponent

@Preview(
    device = PIXEL_TABLET,
)
@Composable
fun SidebarHeaderComponentPreview() {
    SidebarHeaderComponent()
}
