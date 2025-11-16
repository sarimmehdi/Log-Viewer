package com.sarim.header_presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.ui.theme.bodyFontFamily

const val DROP_DOWN_TEXT_COLOR = 0xFF007AD3

@Composable
fun DropDownBoxListItemComponent(
    data: DropDownBoxListItemComponentData,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth()
                .widthIn(max = 500.dp)
                .height(36.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier =
                Modifier
                    .padding(
                        top = 7.dp,
                        start = 9.dp,
                    ).widthIn(max = 450.dp),
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            fontFamily = bodyFontFamily,
            text = data.itemName,
            color = Color(DROP_DOWN_TEXT_COLOR),
        )
        CustomRadioButtonComponent(
            modifier =
                Modifier
                    .padding(top = 11.dp, end = 20.dp),
            data =
                CustomRadioButtonComponentData(
                    on = data.itemFilterOn,
                ),
        )
    }
}

data class DropDownBoxListItemComponentData(
    val itemName: String,
    val itemFilterOn: Boolean,
)
