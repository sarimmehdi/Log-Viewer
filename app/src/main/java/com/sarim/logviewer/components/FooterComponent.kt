package com.sarim.logviewer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.logviewer.R

@Composable
fun FooterComponent(
    modifier: Modifier = Modifier,
    data: FooterComponentData = FooterComponentData(),
) {
    Row(
        modifier =
            modifier
                .width(819.dp)
                .height(33.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            color = Color.White.copy(alpha = 0.8f),
            text = "Showing ${data.numberFirstLogOnPage} - ${data.numberLastLogOnPage} results of ${data.totalLogs}",
        )
        Row {
            CircleWithImageComponent(
                data =
                    CircleWithImageComponentData(
                        imageResource = R.drawable.double_arrows,
                        imageResourceDescription = "Click on this icon to go to the first page of log messages",
                        imageRotation = 180.0f,
                        alpha = 0.8f,
                    ),
            )
            Spacer(
                modifier =
                    Modifier
                        .size(5.dp),
            )
            CircleWithImageComponent(
                data =
                    CircleWithImageComponentData(
                        imageResource = R.drawable.single_arrow,
                        imageResourceDescription = "Click on this icon to go to the previous page of log messages",
                        imageRotation = 180.0f,
                        alpha = 0.8f,
                    ),
            )
            Spacer(
                modifier =
                    Modifier
                        .size(8.33.dp),
            )
            DropDownButtonComponent(
                data =
                    DropDownButtonComponentData(
                        name = String.format("%02d", data.currentPageNumber),
                        borderColor = Color(0xFFFFFFFF).copy(alpha = 0.2f),
                        arrowColor = Color(0xFFFFFFFF).copy(alpha = 0.8f),
                        textColor = Color(0xFFFFFFFF).copy(alpha = 0.8f),
                        fontSize = 12.sp,
                        imageModifier =
                            Modifier
                                .padding(top = 11.67.dp, end = 10.dp)
                                .size(
                                    width = 13.33.dp,
                                    height = 6.67.dp,
                                ),
                        textModifier =
                            Modifier
                                .padding(
                                    top = 8.33.dp,
                                    start = 10.dp,
                                ),
                    ),
                modifier =
                    Modifier
                        .width(56.67.dp)
                        .height(30.dp),
            )
            Spacer(
                modifier =
                    Modifier
                        .size(8.33.dp),
            )
            CircleWithImageComponent(
                data =
                    CircleWithImageComponentData(
                        imageResource = R.drawable.single_arrow,
                        imageResourceDescription = "Click on this icon to go to the next page of log messages",
                        imageRotation = 0.0f,
                        alpha = 1.0f,
                    ),
            )
            Spacer(
                modifier =
                    Modifier
                        .size(5.dp),
            )
            CircleWithImageComponent(
                data =
                    CircleWithImageComponentData(
                        imageResource = R.drawable.double_arrows,
                        imageResourceDescription = "Click on this icon to go to the last page of log messages",
                        imageRotation = 0.0f,
                        alpha = 1.0f,
                    ),
            )
        }
    }
}

data class FooterComponentData(
    val numberFirstLogOnPage: Int = 1,
    val numberLastLogOnPage: Int = 10,
    val totalLogs: Int = 100,
    val currentPageNumber: Int = 1,
)

@Preview(
    device = PIXEL_TABLET,
)
@Composable
fun FooterComponentPreview() {
    FooterComponent()
}
