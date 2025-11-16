package com.sarim.header_presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.sarim.header_presentation.R
import com.sarim.ui.theme.bodyFontFamily

@Composable
fun DropDownButtonComponent(
    data: DropDownButtonComponentData,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .clip(RoundedCornerShape(10.dp))
                .border(
                    width = 1.dp,
                    color = data.borderColor,
                    shape = RoundedCornerShape(10.dp),
                ),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = data.textModifier,
            fontWeight = FontWeight.Normal,
            fontSize = data.fontSize,
            fontFamily = bodyFontFamily,
            text = data.name,
            color = data.textColor,
        )
        Image(
            painter = painterResource(id = R.drawable.dropddown_arrow),
            contentDescription = stringResource(R.string.drop_down_arrow_desc, data.name),
            colorFilter = ColorFilter.tint(data.arrowColor),
            modifier = data.imageModifier,
        )
    }
}

data class DropDownButtonComponentData(
    val name: String,
    val borderColor: Color,
    val arrowColor: Color,
    val textColor: Color,
    val fontSize: TextUnit,
    val imageModifier: Modifier,
    val textModifier: Modifier,
)
