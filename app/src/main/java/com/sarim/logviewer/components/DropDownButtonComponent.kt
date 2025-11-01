package com.sarim.logviewer.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.logviewer.R

@Composable
fun DropDownButtonComponent(
    modifier: Modifier = Modifier,
    data: DropDownButtonComponentData = DropDownButtonComponentData(),
) {
    Row(
        modifier =
            modifier
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(
                    width = 1.dp,
                    color = Color(0xFF007AD3),
                    shape = RoundedCornerShape(10.dp),
                ),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier =
                Modifier
                    .padding(
                        top = 10.dp,
                        start = 10.dp,
                    ),
            fontStyle = FontStyle.Normal,
            fontSize = 24.sp,
            fontFamily =
                FontFamily(
                    Font(R.font.inter_24_regular, FontWeight.Normal),
                    Font(R.font.inter_24_medium, FontWeight.Medium),
                    Font(R.font.inter_24_bold, FontWeight.Bold),
                ),
            text = data.name,
            color = Color(0xFF007AD3),
        )
        Image(
            painter = painterResource(id = R.drawable.dropddown_arrow),
            contentDescription = "Icon to open the drop down for ${data.name}",
            modifier =
                Modifier
                    .padding(top = 21.dp, end = 16.6.dp)
                    .size(
                        width = 18.4.dp,
                        height = 8.dp,
                    ),
        )
    }
}

data class DropDownButtonComponentData(
    val name: String = "",
)

@Preview(device = PIXEL_TABLET)
@Composable
fun DropDownButtonComponentPreview(
    @PreviewParameter(DropDownButtonPreviewParameterProvider::class) previewData: DropDownButtonPreviewData,
) {
    DropDownButtonComponent(
        data = previewData.data,
        modifier = previewData.modifier,
    )
}

data class DropDownButtonPreviewData(
    val data: DropDownButtonComponentData,
    val modifier: Modifier = Modifier,
)

class DropDownButtonPreviewParameterProvider : PreviewParameterProvider<DropDownButtonPreviewData> {
    override val values =
        sequenceOf(
            DropDownButtonPreviewData(
                data = DropDownButtonComponentData(name = "Class"),
                modifier = Modifier.width(138.75.dp),
            ),
            DropDownButtonPreviewData(
                data = DropDownButtonComponentData(name = "Function"),
                modifier = Modifier.width(154.16.dp),
            ),
            DropDownButtonPreviewData(
                data = DropDownButtonComponentData(name = "Level"),
                modifier = Modifier.width(138.75.dp),
            ),
        )
}
