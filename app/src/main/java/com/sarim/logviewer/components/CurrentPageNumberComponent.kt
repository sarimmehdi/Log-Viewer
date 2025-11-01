package com.sarim.logviewer.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
import java.util.Locale

@Composable
fun CurrentPageNumberComponent(
    modifier: Modifier = Modifier,
    data: CurrentPageNumberComponentData = CurrentPageNumberComponentData(),
) {
    Box(
        modifier =
            modifier
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(5.dp),
                ).height(30.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            fontFamily =
                FontFamily(
                    Font(R.font.inter_24_regular, FontWeight.Normal),
                    Font(R.font.inter_24_medium, FontWeight.Medium),
                    Font(R.font.inter_24_bold, FontWeight.Bold),
                ),
            text = String.format(Locale.ROOT, "%02d", data.currentPageNumber),
            color = Color.White.copy(alpha = 0.2f),
            modifier =
                Modifier
                    .padding(horizontal = 10.dp),
        )
    }
}

data class CurrentPageNumberComponentData(
    val currentPageNumber: Int = 1,
)

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun SearchboxComponentPreview(
    @PreviewParameter(CurrentPageNumberComponentDataParameterProvider::class) data: CurrentPageNumberComponentData,
) {
    CurrentPageNumberComponent(
        data = data,
    )
}

class CurrentPageNumberComponentDataParameterProvider : PreviewParameterProvider<CurrentPageNumberComponentData> {
    override val values =
        sequenceOf(
            CurrentPageNumberComponentData(
                currentPageNumber = 9,
            ),
            CurrentPageNumberComponentData(
                currentPageNumber = 99,
            ),
            CurrentPageNumberComponentData(
                currentPageNumber = 999,
            ),
            CurrentPageNumberComponentData(
                currentPageNumber = 9999,
            ),
        )
}
