package com.sarim.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private const val TOTAL_ITEMS = 30

@Preview(
    device = PIXEL_TABLET,
)
@Composable
private fun CustomScrollableListComponentPreview() {
    CustomScrollableListComponent(
        customScrollableListComponentData =
            CustomScrollableListComponentData(
                contentHeight = 500.dp,
            ) {
                items(TOTAL_ITEMS) {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .padding(4.dp)
                                .background(Color.White),
                    )
                }
            },
    )
}
