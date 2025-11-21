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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sarim.footer_domain.usecase.ChangeCurrentPageNumUseCase
import com.sarim.footer_presentation.FooterScreenTags.nextSingleArrowButtonTestTag
import com.sarim.footer_presentation.FooterScreenTags.nextDoubleArrowButtonTestTag
import com.sarim.footer_presentation.FooterScreenTags.prevSingleArrowButtonTestTag
import com.sarim.footer_presentation.FooterScreenTags.prevDoubleArrowButtonTestTag
import com.sarim.footer_presentation.component.CircleWithImageComponent
import com.sarim.footer_presentation.component.CircleWithImageComponentData
import com.sarim.footer_presentation.component.CurrentPageNumberComponent
import com.sarim.footer_presentation.component.CurrentPageNumberComponentData

const val CLICKABLE_ALPHA = 1.0f
const val UNCLICKABLE_ALPHA = 0.8f

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
            val canGoToPreviousPageAlpha =
                if (data.canGoToPreviousPage) {
                    CLICKABLE_ALPHA
                } else {
                    UNCLICKABLE_ALPHA
                }
            val canGoToNextPageAlpha =
                if (data.canGoToNextPage) {
                    CLICKABLE_ALPHA
                } else {
                    UNCLICKABLE_ALPHA
                }
            CircleWithImageComponent(
                data =
                    CircleWithImageComponentData(
                        imageResource = R.drawable.double_arrows,
                        imageResourceDescription = stringResource(R.string.jump_to_first_page_icon_desc),
                        imageRotation = 180.0f,
                        alpha = canGoToPreviousPageAlpha,
                    ),
                modifier =
                    Modifier
                        .clickable {
                            onEvent(
                                FooterScreenToViewModelEvents.ChangeCurrentPageNumber(1),
                            )
                        }.testTag(prevDoubleArrowButtonTestTag(canGoToPreviousPageAlpha)),
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
                        alpha = canGoToPreviousPageAlpha,
                    ),
                modifier =
                    Modifier
                        .clickable {
                            onEvent(
                                FooterScreenToViewModelEvents.ChangeCurrentPageNumberByOne(
                                    ChangeCurrentPageNumUseCase.ChangeType.DECREASE,
                                ),
                            )
                        }.testTag(prevSingleArrowButtonTestTag(canGoToPreviousPageAlpha)),
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
                        alpha = canGoToNextPageAlpha,
                    ),
                modifier =
                    Modifier
                        .clickable {
                            onEvent(
                                FooterScreenToViewModelEvents.ChangeCurrentPageNumberByOne(
                                    ChangeCurrentPageNumUseCase.ChangeType.INCREASE,
                                ),
                            )
                        }.testTag(nextSingleArrowButtonTestTag(canGoToNextPageAlpha)),
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
                        alpha = canGoToNextPageAlpha,
                    ),
                modifier =
                    Modifier
                        .clickable {
                            onEvent(
                                FooterScreenToViewModelEvents.ChangeCurrentPageNumber(data.totalPages),
                            )
                        }.testTag(nextDoubleArrowButtonTestTag(canGoToNextPageAlpha)),
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

object FooterScreenTags {
    private const val P = "FOOTER_SCREEN"
    private const val S = "_TEST_TAG"

    fun nextSingleArrowButtonTestTag(alpha: Float) = "${P}NEXT_SINGLE_ARROW_$alpha$S"

    fun nextDoubleArrowButtonTestTag(alpha: Float) = "${P}NEXT_DOUBLE_ARROWS_$alpha$S"

    fun prevSingleArrowButtonTestTag(alpha: Float) = "${P}PREV_SINGLE_ARROW_$alpha$S"

    fun prevDoubleArrowButtonTestTag(alpha: Float) = "${P}PREV_DOUBLE_ARROW_$alpha$S"
}
