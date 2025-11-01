package com.sarim.logviewer.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
    var text by remember {
        mutableStateOf(
            String.format(Locale.ROOT, "%02d", data.currentPageNumber),
        )
    }
    BasicTextField(
        value = text,
        onValueChange = { input ->
            if (input.all { it.isDigit() }) text = input.take(2)
        },
        modifier =
            modifier
                .clip(RoundedCornerShape(5.dp))
                .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(5.dp)),
        textStyle =
            LocalTextStyle.current.copy(
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                fontFamily =
                    FontFamily(
                        Font(R.font.inter_24_regular, FontWeight.Normal),
                        Font(R.font.inter_24_medium, FontWeight.Medium),
                        Font(R.font.inter_24_bold, FontWeight.Bold),
                    ),
            ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
        keyboardActions =
            KeyboardActions(onDone = {
                text.toIntOrNull()?.let {
                    text = String.format(Locale.ROOT, "%02d", it)
                }
            }),
        cursorBrush = SolidColor(Color.White.copy(alpha = 0.8f)),
        decorationBox = { innerTextField ->
            Box(
                modifier =
                    Modifier
                        .widthIn(max = 50.dp)
                        .padding(10.dp),
            ) {
                innerTextField()
            }
        },
    )
}

data class CurrentPageNumberComponentData(
    val currentPageNumber: Int = 1,
)

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun CurrentPageNumberComponentPreview(
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
