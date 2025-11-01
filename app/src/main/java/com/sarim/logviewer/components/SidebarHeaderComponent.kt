package com.sarim.logviewer.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.logviewer.R

@Composable
fun SidebarHeaderComponent(modifier: Modifier = Modifier) {
    Row(
        modifier =
            modifier
                .width(425.dp)
                .height(100.dp),
    ) {
        Text(
            text = "Dates",
            modifier =
                Modifier
                    .padding(start = 33.dp, top = 20.dp),
            fontStyle = FontStyle.Normal,
            fontSize = 48.sp,
            color = Color.White,
        )
        Spacer(
            modifier =
                Modifier
                    .size(173.dp),
        )
        Image(
            painter = painterResource(id = R.drawable.side_bar_close_icon),
            contentDescription = "Icon to close the sidebar",
            modifier =
                Modifier
                    .padding(top = 36.dp)
                    .size(26.33.dp),
        )
    }
}

@Preview
@Composable
fun SidebarHeaderComponentPreview() {
    SidebarHeaderComponent()
}
