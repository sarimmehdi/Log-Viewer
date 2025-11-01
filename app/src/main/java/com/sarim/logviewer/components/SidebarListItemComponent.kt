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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.logviewer.R

@Composable
fun SidebarListItemComponent(
    modifier: Modifier = Modifier,
    data: SidebarListItemComponentData = SidebarListItemComponentData(),
) {
    Row(
        modifier =
            modifier
                .width(425.dp)
                .height(78.dp)
                .then(
                    if (data.selected) {
                        Modifier.background(Color(0xFFD9D9D9).copy(alpha = 0.1f))
                    } else {
                        Modifier
                    },
                ),
    ) {
        if (data.selected) {
            Box(
                modifier =
                    Modifier
                        .width(20.dp)
                        .height(78.dp)
                        .background(Color.White),
            )
        }
        Column {
            Text(
                text = data.heading,
                modifier =
                    Modifier
                        .padding(
                            start =
                                if (data.selected) {
                                    13.dp
                                } else {
                                    33.dp
                                },
                            top = 5.dp,
                        ),
                fontStyle = FontStyle.Normal,
                fontSize = 24.sp,
                fontFamily =
                    FontFamily(
                        Font(R.font.inter_24_regular, FontWeight.Normal),
                        Font(R.font.inter_24_medium, FontWeight.Medium),
                        Font(R.font.inter_24_bold, FontWeight.Bold),
                    ),
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(
                modifier =
                    Modifier
                        .size(5.dp),
            )
            Text(
                text = data.subHeading,
                modifier =
                    Modifier
                        .padding(
                            start =
                                if (data.selected) {
                                    13.dp
                                } else {
                                    33.dp
                                },
                        ),
                fontStyle = FontStyle.Normal,
                fontSize = 20.sp,
                fontFamily =
                    FontFamily(
                        Font(R.font.inter_24_regular, FontWeight.Normal),
                        Font(R.font.inter_24_medium, FontWeight.Medium),
                        Font(R.font.inter_24_bold, FontWeight.Bold),
                    ),
                color = Color.White.copy(alpha = 0.5f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

data class SidebarListItemComponentData(
    val heading: String = "",
    val subHeading: String = "",
    val selected: Boolean = false,
)

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun SidebarListItemComponentPreview(
    @PreviewParameter(SidebarListItemComponentDataParameterProvider::class) data: SidebarListItemComponentData,
) {
    SidebarListItemComponent(
        data = data,
    )
}

class SidebarListItemComponentDataParameterProvider : PreviewParameterProvider<SidebarListItemComponentData> {
    override val values =
        sequenceOf(
            SidebarListItemComponentData(
                heading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                subHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                selected = true,
            ),
            SidebarListItemComponentData(
                heading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                subHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                selected = false,
            ),
        )
}
