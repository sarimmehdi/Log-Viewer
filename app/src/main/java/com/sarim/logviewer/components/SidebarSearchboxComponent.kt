package com.sarim.logviewer.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.logviewer.R

@Composable
fun SidebarSearchboxComponent(
    data: SidebarSearchboxComponentData = SidebarSearchboxComponentData(),
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .width(337.dp)
            .height(52.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFF01070B)),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = data.placeholderText,
            modifier = Modifier
                .padding(start = 18.dp, top = 10.dp),
            fontStyle = FontStyle.Normal,
            fontSize = 24.sp,
            color = Color.White.copy(alpha = 0.2f),
        )
        Image(
            painter = painterResource(id = R.drawable.magnifying_glass),
            contentDescription = data.iconDescription,
            modifier = Modifier
                .padding(end = 16.44.dp, top = 16.dp)
                .size(
                    width = 19.71.dp,
                    height = 19.56.dp
                )
        )
    }
}

data class SidebarSearchboxComponentData(
    val placeholderText: String = "",
    val iconDescription: String = "",
)

@Composable
@Preview
internal fun SidebarSearchboxComponentPreview(
    @PreviewParameter(SidebarSearchboxComponentDataParameterProvider::class) data: SidebarSearchboxComponentData,
) {
    SidebarSearchboxComponent(
        data = data,
    )
}

class SidebarSearchboxComponentDataParameterProvider :
    PreviewParameterProvider<SidebarSearchboxComponentData> {
    override val values = sequenceOf(
        SidebarSearchboxComponentData(
            placeholderText = "Search dates",
            iconDescription = "Icon to search dates",
        ),
        SidebarSearchboxComponentData(
            placeholderText = "Search sessions",
            iconDescription = "Icon to search sessions",
        )
    )
}