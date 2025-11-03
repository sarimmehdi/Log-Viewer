package com.sarim.logviewer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

const val RADIO_BUTTON_ON_COLOR = 0xFF03111B
const val RADIO_BUTTON_ON_BORDER_COLOR = 0xFF004AD3
const val RADIO_BUTTON_OFF_COLOR = 0xFF007AD3

@Composable
fun CustomRadioButtonComponent(
    data: CustomRadioButtonComponentData,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .width(41.dp)
                .height(22.dp)
                .clip(RoundedCornerShape(13.5.dp))
                .border(
                    1.dp,
                    if (data.on) {
                        Color(RADIO_BUTTON_ON_COLOR)
                    } else {
                        Color(RADIO_BUTTON_OFF_COLOR)
                    },
                    RoundedCornerShape(13.5.dp),
                ).then(
                    if (data.on) Modifier.background(Color(RADIO_BUTTON_ON_BORDER_COLOR)) else Modifier,
                ),
        contentAlignment =
            if (data.on) {
                Alignment.CenterEnd
            } else {
                Alignment.CenterStart
            },
    ) {
        if (data.on) {
            Box(
                modifier =
                    Modifier
                        .padding(
                            end = 4.dp,
                        ).size(width = 12.91.dp, height = 13.85.dp)
                        .background(
                            color = Color(RADIO_BUTTON_ON_COLOR),
                            shape = RoundedCornerShape(percent = 50),
                        ),
            )
        } else {
            Box(
                modifier =
                    Modifier
                        .padding(
                            start = 4.dp,
                        ).size(width = 12.91.dp, height = 13.85.dp)
                        .background(
                            color = Color(RADIO_BUTTON_OFF_COLOR),
                            shape = RoundedCornerShape(percent = 50),
                        ),
            )
        }
    }
}

data class CustomRadioButtonComponentData(
    val on: Boolean,
)

// @Composable
// @Preview(
//    device = PIXEL_TABLET,
// )
// internal fun CustomRadioButtonComponentPreview(
//    @PreviewParameter(CustomRadioButtonComponentDataParameterProvider::class) data: CustomRadioButtonComponentData,
// ) {
//    CustomRadioButtonComponent(
//        data = data,
//    )
// }
//
// class CustomRadioButtonComponentDataParameterProvider : PreviewParameterProvider<CustomRadioButtonComponentData> {
//    override val values =
//        sequenceOf(
//            CustomRadioButtonComponentData(
//                on = true,
//            ),
//            CustomRadioButtonComponentData(
//                on = false,
//            ),
//        )
// }
