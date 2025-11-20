package com.sarim.footer_presentation.component

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.ui.theme.bodyFontFamily
import java.util.Locale
import com.sarim.footer_presentation.component.CurrentPageNumberComponentTags.TEXT_FIELD

@Composable
fun CurrentPageNumberComponent(
    modifier: Modifier = Modifier,
    data: CurrentPageNumberComponentData,
    onDone: (Int) -> Unit,
) {
    var text by remember {
        mutableStateOf(
            String.format(Locale.ROOT, "%02d", data.currentPageNumber),
        )
    }
    LaunchedEffect(data.currentPageNumber) {
        text = String.format(Locale.ROOT, "%02d", data.currentPageNumber)
    }
    val focusManager = LocalFocusManager.current
    BasicTextField(
        value = text,
        onValueChange = { input ->
            text = input.filter { it.isDigit() }
        },
        modifier =
            modifier
                .clip(RoundedCornerShape(5.dp))
                .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(5.dp))
                .testTag(TEXT_FIELD),
        textStyle =
            LocalTextStyle.current.copy(
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                fontFamily = bodyFontFamily,
            ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
        keyboardActions =
            KeyboardActions(onDone = {
                text.toIntOrNull()?.let {
                    onDone(it)
                    focusManager.clearFocus()
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
    val currentPageNumber: Int,
)

object CurrentPageNumberComponentTags {
    private const val P = "CURRENT_PAGE_NUMBER_COMPONENT_"
    private const val S = "_TEST_TAG"
    const val TEXT_FIELD = "${P}TEXT_FIELD$S"
}
