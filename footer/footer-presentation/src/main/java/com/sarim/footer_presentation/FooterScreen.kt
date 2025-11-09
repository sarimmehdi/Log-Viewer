package com.sarim.footer_presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sarim.footer_domain.usecase.ChangeType
import com.sarim.footer_presentation.component.CircleWithImageComponent
import com.sarim.footer_presentation.component.CircleWithImageComponentData
import com.sarim.footer_presentation.component.CurrentPageNumberComponent
import com.sarim.footer_presentation.component.CurrentPageNumberComponentData

@Composable
fun FooterScreen(
    modifier: Modifier = Modifier,
    data: FooterScreenData,
    onEvent: (FooterScreenToViewModelEvents) -> Unit,
) {
    Row(
        modifier =
            modifier
                .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            color = Color.White.copy(alpha = 0.8f),
            text =
                pluralStringResource(
                    id = R.plurals.showing_results,
                    count = data.totalLogs,
                    data.numberFirstLogOnPage,
                    data.numberLastLogOnPage,
                    data.totalLogs,
                ),
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CircleWithImageComponent(
                data =
                    CircleWithImageComponentData(
                        imageResource = R.drawable.double_arrows,
                        imageResourceDescription = stringResource(R.string.jump_to_first_page_icon_desc),
                        imageRotation = 180.0f,
                        alpha =
                            if (data.canGoToPreviousPage) {
                                1.0f
                            } else {
                                0.8f
                            },
                    ),
                modifier =
                    Modifier.clickable {
                        onEvent(
                            FooterScreenToViewModelEvents.ChangeCurrentPageNumber(1),
                        )
                    },
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
                        imageResourceDescription = stringResource(R.string.jump_to_previous_page_icon_desc),
                        imageRotation = 180.0f,
                        alpha =
                            if (data.canGoToPreviousPage) {
                                1.0f
                            } else {
                                0.8f
                            },
                    ),
                modifier =
                    Modifier.clickable {
                        onEvent(
                            FooterScreenToViewModelEvents.ChangeCurrentPageNumberByOne(ChangeType.DECREASE),
                        )
                    },
            )
            Spacer(
                modifier =
                    Modifier
                        .size(8.33.dp),
            )
            CurrentPageNumberComponent(
                data =
                    CurrentPageNumberComponentData(
                        currentPageNumber = data.currentPageNumber,
                    ),
                onDone = {
                    onEvent(
                        FooterScreenToViewModelEvents.ChangeCurrentPageNumber(it),
                    )
                },
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
                        imageResourceDescription = stringResource(R.string.jump_to_next_page_icon_desc),
                        imageRotation = 0.0f,
                        alpha =
                            if (data.canGoToNextPage) {
                                1.0f
                            } else {
                                0.8f
                            },
                    ),
                modifier =
                    Modifier.clickable {
                        onEvent(
                            FooterScreenToViewModelEvents.ChangeCurrentPageNumberByOne(ChangeType.INCREASE),
                        )
                    },
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
                        imageResourceDescription = stringResource(R.string.jump_to_last_page_icon_desc),
                        imageRotation = 0.0f,
                        alpha =
                            if (data.canGoToNextPage) {
                                1.0f
                            } else {
                                0.8f
                            },
                    ),
                modifier =
                    Modifier.clickable {
                        onEvent(
                            FooterScreenToViewModelEvents.ChangeCurrentPageNumber(data.totalPages),
                        )
                    },
            )
        }
    }
}

data class FooterScreenData(
    val numberFirstLogOnPage: Int,
    val numberLastLogOnPage: Int,
    val totalLogs: Int,
    val currentPageNumber: Int,
    val totalPages: Int,
    val canGoToNextPage: Boolean,
    val canGoToPreviousPage: Boolean,
)
