package com.sarim.logviewer.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.utils.component.SearchboxComponent
import com.sarim.utils.component.SearchboxComponentData

const val SEARCH_BOX_BACKGROUND_COLOR = 0xFF03111B
const val DROP_DOWN_BUTTON_COLOR = 0xFF007AD3

@Composable
fun HeaderComponent(modifier: Modifier = Modifier) {
    Row(
        modifier =
            modifier
                .width(819.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SearchboxComponent(
            data =
                SearchboxComponentData(
                    placeholderText = "Search messages",
                    iconDescription = "Icon to search messages",
                    backgroundColor = Color(SEARCH_BOX_BACKGROUND_COLOR),
                    onValueChange = {},
                ),
        )
        Spacer(
            modifier =
                Modifier
                    .size(19.27.dp),
        )
        DropDownButtonComponent(
            data =
                DropDownButtonComponentData(
                    name = "Class",
                    borderColor = Color(DROP_DOWN_BUTTON_COLOR),
                    arrowColor = Color(DROP_DOWN_BUTTON_COLOR),
                    textColor = Color(DROP_DOWN_BUTTON_COLOR),
                    fontSize = 24.sp,
                    imageModifier =
                        Modifier
                            .padding(top = 21.dp, end = 16.6.dp)
                            .size(
                                width = 18.4.dp,
                                height = 8.dp,
                            ),
                    textModifier =
                        Modifier
                            .padding(
                                top = 10.dp,
                                start = 10.dp,
                            ),
                ),
            modifier =
                Modifier
                    .width(138.75.dp)
                    .height(50.dp),
        )
        Spacer(
            modifier =
                Modifier
                    .size(21.2.dp),
        )
        DropDownButtonComponent(
            data =
                DropDownButtonComponentData(
                    name = "Function",
                    borderColor = Color(DROP_DOWN_BUTTON_COLOR),
                    arrowColor = Color(DROP_DOWN_BUTTON_COLOR),
                    textColor = Color(DROP_DOWN_BUTTON_COLOR),
                    fontSize = 24.sp,
                    imageModifier =
                        Modifier
                            .padding(top = 21.dp, end = 16.6.dp)
                            .size(
                                width = 18.4.dp,
                                height = 8.dp,
                            ),
                    textModifier =
                        Modifier
                            .padding(
                                top = 10.dp,
                                start = 10.dp,
                            ),
                ),
            modifier =
                Modifier
                    .width(154.16.dp)
                    .height(50.dp),
        )
        Spacer(
            modifier =
                Modifier
                    .size(22.16.dp),
        )
        DropDownButtonComponent(
            data =
                DropDownButtonComponentData(
                    name = "Level",
                    borderColor = Color(DROP_DOWN_BUTTON_COLOR),
                    arrowColor = Color(DROP_DOWN_BUTTON_COLOR),
                    textColor = Color(DROP_DOWN_BUTTON_COLOR),
                    fontSize = 24.sp,
                    imageModifier =
                        Modifier
                            .padding(top = 21.dp, end = 16.6.dp)
                            .size(
                                width = 18.4.dp,
                                height = 8.dp,
                            ),
                    textModifier =
                        Modifier
                            .padding(
                                top = 10.dp,
                                start = 10.dp,
                            ),
                ),
            modifier =
                Modifier
                    .width(138.75.dp)
                    .height(50.dp),
        )
    }
}

// @Preview(
//    device = PIXEL_TABLET,
// )
// @Composable
// fun HeaderComponentPreview() {
//    HeaderComponent(
//        modifier =
//            Modifier
//                .background(Color(0xFF01070B)),
//    )
// }
