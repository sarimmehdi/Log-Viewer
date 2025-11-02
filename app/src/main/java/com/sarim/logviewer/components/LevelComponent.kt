package com.sarim.logviewer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.logviewer.R

@Composable
fun LevelComponent(
    data: LevelComponentData,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .defaultMinSize(minWidth = 56.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(data.color),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            fontWeight = FontWeight.Black,
            fontSize = 12.sp,
            fontFamily =
                FontFamily(
                    Font(R.font.inter_24_regular, FontWeight.Normal),
                    Font(R.font.inter_24_medium, FontWeight.Medium),
                    Font(R.font.inter_24_bold, FontWeight.Bold),
                ),
            text = data.name,
            color = Color.White,
            modifier =
                Modifier
                    .padding(4.dp),
        )
    }
}

data class LevelComponentData(
    val name: String,
    val color: Color,
)

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun LevelComponentPreview(
    @PreviewParameter(LevelComponentDataParameterProvider::class) data: LevelComponentData,
) {
    LevelComponent(
        data = data,
    )
}

class LevelComponentDataParameterProvider : PreviewParameterProvider<LevelComponentData> {
    override val values =
        sequenceOf(
            LevelComponentData(
                name = "ERROR",
                color = Color(0xFFD30000),
            ),
            LevelComponentData(
                name = "DEBUG",
                color = Color(0xFF004AD3),
            ),
            LevelComponentData(
                name = "INFO",
                color = Color(0xFF3FD300),
            ),
            LevelComponentData(
                name = "WARN",
                color = Color(0xFFD36600),
            ),
            LevelComponentData(
                name = "CRITICAL",
                color = Color(0xFFFF0004),
            ),
        )
}
