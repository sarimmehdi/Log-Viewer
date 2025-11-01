package com.sarim.utils.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.utils.R

@Composable
fun SearchboxComponent(
    modifier: Modifier = Modifier,
    data: SearchboxComponentData = SearchboxComponentData(),
) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { text = it },
        modifier =
            modifier
                .width(337.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(data.backgroundColor),
        placeholder = {
            Text(
                text = data.placeholderText,
                fontSize = 24.sp,
                fontFamily =
                    FontFamily(
                        Font(R.font.inter_24_regular, FontWeight.Normal),
                        Font(R.font.inter_24_medium, FontWeight.Medium),
                        Font(R.font.inter_24_bold, FontWeight.Bold),
                    ),
                color = Color.White.copy(alpha = 0.2f),
            )
        },
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(color = Color.White, fontSize = 24.sp),
        trailingIcon = {
            val icon = if (text.isEmpty()) R.drawable.magnifying_glass else R.drawable.side_bar_close_icon
            IconButton(
                onClick = { if (text.isNotEmpty()) text = "" },
                modifier =
                    Modifier.then(
                        if (text.isNotEmpty()) Modifier.size(19.71.dp, 19.56.dp) else Modifier,
                    ),
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = data.iconDescription,
                )
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { }),
        colors =
            TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
    )
}

data class SearchboxComponentData(
    val placeholderText: String = "",
    val iconDescription: String = "",
    val backgroundColor: Color = Color.Transparent,
)

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun SearchboxComponentPreview(
    @PreviewParameter(SearchboxComponentDataParameterProvider::class) data: SearchboxComponentData,
) {
    SearchboxComponent(
        data = data,
    )
}

class SearchboxComponentDataParameterProvider : PreviewParameterProvider<SearchboxComponentData> {
    override val values =
        sequenceOf(
            SearchboxComponentData(
                placeholderText = "Search dates",
                iconDescription = "Icon to search dates",
                backgroundColor = Color(0xFF01070B),
            ),
            SearchboxComponentData(
                placeholderText = "Search sessions",
                iconDescription = "Icon to search sessions",
                backgroundColor = Color(0xFF01070B),
            ),
            SearchboxComponentData(
                placeholderText = "Search messages",
                iconDescription = "Icon to search messages",
                backgroundColor = Color(0xFF03111B),
            ),
        )
}
