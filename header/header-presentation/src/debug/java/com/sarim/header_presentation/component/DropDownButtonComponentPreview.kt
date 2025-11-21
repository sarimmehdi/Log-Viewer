package com.sarim.header_presentation.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private const val DROP_DOWN_BUTTON_COLOR = 0xFF007AD3

@Preview(device = PIXEL_TABLET)
@Composable
internal fun DropDownButtonComponentPreview(
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

internal class DropDownButtonPreviewParameterProvider : PreviewParameterProvider<DropDownButtonPreviewData> {
    override val values =
        sequenceOf(
            DropDownButtonPreviewData(
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
            ),
            DropDownButtonPreviewData(
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
            ),
            DropDownButtonPreviewData(
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
            ),
        )
}
