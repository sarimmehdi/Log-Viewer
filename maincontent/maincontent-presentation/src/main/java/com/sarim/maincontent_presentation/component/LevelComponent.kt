package com.sarim.maincontent_presentation.component

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.utils.R

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
