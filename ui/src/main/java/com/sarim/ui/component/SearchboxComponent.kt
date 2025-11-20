package com.sarim.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.ui.component.SearchboxComponentTags.TEXT_FIELD
import com.sarim.ui.component.SearchboxComponentTags.iconTestTag
import com.sarim.utils.R

@Composable
fun SearchboxComponent(
    data: SearchboxComponentData,
    modifier: Modifier = Modifier,
) {
    var text by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    TextField(
        value = text,
        onValueChange = {
            text = it
            data.onValueChange(it)
        },
        modifier =
            modifier
                .width(337.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(data.backgroundColor)
                .testTag(TEXT_FIELD),
        placeholder = {
            Text(
                text = data.placeholderText,
                style = MaterialTheme.typography.labelMedium,
                color = Color.White.copy(alpha = 0.2f),
            )
        },
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(color = Color.White, fontSize = 24.sp),
        trailingIcon = {
            val icon =
                if (text.isEmpty()) R.drawable.magnifying_glass else R.drawable.side_bar_close_icon
            IconButton(
                onClick = {
                    if (text.isNotEmpty()) {
                        text = ""
                        data.onValueChange("")
                        focusManager.clearFocus()
                    }
                },
                modifier =
                    Modifier.then(
                        if (text.isNotEmpty()) Modifier.size(19.71.dp, 19.56.dp) else Modifier,
                    ),
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = data.iconDescription,
                    modifier = Modifier.testTag(iconTestTag(icon)),
                )
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
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
    val placeholderText: String,
    val iconDescription: String,
    val backgroundColor: Color,
    val onValueChange: (String) -> Unit,
)

object SearchboxComponentTags {
    private const val P = "SEARCH_BOX_COMPONENT_"
    private const val S = "_TEST_TAG"
    const val TEXT_FIELD = "${P}TEXT_FIELD$S"

    fun iconTestTag(resId: Int) = "${P}ICON$resId$S"
}
