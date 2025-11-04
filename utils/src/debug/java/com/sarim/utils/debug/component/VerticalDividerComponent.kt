package com.sarim.utils.debug.component

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sarim.utils.component.VerticalDividerComponent

@Preview(
    device = PIXEL_TABLET,
)
@Composable
fun VerticalDividerComponentPreview() {
    VerticalDividerComponent(
        modifier =
            Modifier
                .height(645.dp),
    )
}
