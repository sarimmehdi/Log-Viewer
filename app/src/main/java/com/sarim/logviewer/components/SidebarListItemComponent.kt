package com.sarim.logviewer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SidebarListItemComponent(
    data: SidebarListItemComponentData = SidebarListItemComponentData(),
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .width(425.dp)
            .height(78.dp)
            .then(
                if (data.selected) Modifier.background(Color(0xFFD9D9D9).copy(alpha = 0.1f))
                else Modifier
            )
    ) {
        if (data.selected) {
            Box(
                modifier = Modifier
                    .width(20.dp)
                    .height(78.dp)
                    .background(Color.White)
            )
        }
        Column {
            Text(
                text = data.heading,
                modifier = Modifier
                    .padding(
                        start = if (data.selected) {
                            13.dp
                        } else {
                            33.dp
                        },
                        top = 5.dp
                    ),
                fontStyle = FontStyle.Normal,
                fontSize = 24.sp,
                color = Color.White
            )
            Spacer(
                modifier = Modifier
                    .size(5.dp)
            )
            Text(
                text = data.subHeading,
                modifier = Modifier
                    .padding(
                        start = if (data.selected) {
                            13.dp
                        } else {
                            33.dp
                        }
                    ),
                fontStyle = FontStyle.Normal,
                fontSize = 20.sp,
                color = Color.White.copy(alpha = 0.5f)
            )
        }
    }
}

data class SidebarListItemComponentData(
    val heading: String = "",
    val subHeading: String = "",
    val selected: Boolean = false
)

@Composable
@Preview
internal fun SidebarListItemComponentPreview(
    @PreviewParameter(SidebarListItemComponentDataParameterProvider::class) data: SidebarListItemComponentData,
) {
    SidebarListItemComponent(
        data = data,
    )
}

class SidebarListItemComponentDataParameterProvider :
    PreviewParameterProvider<SidebarListItemComponentData> {
    override val values = sequenceOf(
        SidebarListItemComponentData(
            heading = "Date 1",
            subHeading = "9 sessions",
            selected = true
        ),
        SidebarListItemComponentData(
            heading = "Date 1",
            subHeading = "9 sessions",
            selected = false
        ),
        SidebarListItemComponentData(
            heading = "Session 1",
            subHeading = "10 messages",
            selected = true
        ),
        SidebarListItemComponentData(
            heading = "Session 1",
            subHeading = "10 messages",
            selected = false
        )
    )
}