package com.sarim.logviewer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
fun DropDownBoxListItemComponent(
    modifier: Modifier = Modifier,
    data: DropDownBoxListItemComponentData = DropDownBoxListItemComponentData(),
) {
    Row(
        modifier =
            modifier
                .width(154.dp)
                .height(36.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier =
                Modifier
                    .padding(
                        top = 7.dp,
                        start = 9.dp,
                    ),
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            fontFamily =
                FontFamily(
                    Font(R.font.inter_24_regular, FontWeight.Normal),
                    Font(R.font.inter_24_medium, FontWeight.Medium),
                    Font(R.font.inter_24_bold, FontWeight.Bold),
                ),
            text = data.itemName,
            color = Color(0xFF007AD3),
        )
        CustomRadioButtonComponent(
            modifier =
                Modifier
                    .padding(top = 11.dp, end = 10.dp),
            data =
                CustomRadioButtonComponentData(
                    on = data.itemFilterOn,
                ),
        )
    }
}

data class DropDownBoxListItemComponentData(
    val itemName: String = "",
    val itemFilterOn: Boolean = true,
)

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun DropDownBoxListItemComponentPreview(
    @PreviewParameter(DropDownBoxListItemComponentDataParameterProvider::class) data: DropDownBoxListItemComponentData,
) {
    DropDownBoxListItemComponent(
        data = data,
    )
}

class DropDownBoxListItemComponentDataParameterProvider : PreviewParameterProvider<DropDownBoxListItemComponentData> {
    override val values =
        sequenceOf(
            DropDownBoxListItemComponentData(
                itemName = "Class 1",
                itemFilterOn = true,
            ),
            DropDownBoxListItemComponentData(
                itemName = "Class 1",
                itemFilterOn = false,
            ),
        )
}
