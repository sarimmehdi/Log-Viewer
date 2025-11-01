package com.sarim.logviewer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HeaderComponent(modifier: Modifier = Modifier) {
    Row(
        modifier =
            modifier
                .width(819.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SearchboxComponent(
            data =
                SearchboxComponentData(
                    placeholderText = "Search messages",
                    iconDescription = "Icon to search messages",
                    backgroundColor = Color(0xFF03111B),
                ),
        )
        Spacer(
            modifier =
                Modifier
                    .size(19.27.dp),
        )
        DropDownButtonComponent(
            data = DropDownButtonComponentData(name = "Class"),
            modifier = Modifier.width(138.75.dp),
        )
        Spacer(
            modifier =
                Modifier
                    .size(21.2.dp),
        )
        DropDownButtonComponent(
            data = DropDownButtonComponentData(name = "Function"),
            modifier = Modifier.width(154.16.dp),
        )
        Spacer(
            modifier =
                Modifier
                    .size(22.16.dp),
        )
        DropDownButtonComponent(
            data = DropDownButtonComponentData(name = "Level"),
            modifier = Modifier.width(138.75.dp),
        )
    }
}

@Preview(
    device = PIXEL_TABLET,
)
@Composable
fun HeaderComponentPreview() {
    HeaderComponent(
        modifier =
            Modifier
                .background(Color(0xFF01070B)),
    )
}
