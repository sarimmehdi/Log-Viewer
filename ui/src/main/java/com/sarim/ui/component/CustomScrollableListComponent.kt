package com.sarim.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.sarim.ui.component.CustomScrollableListComponentTags.LAZY_COLUMN
import com.sarim.ui.component.CustomScrollableListComponentTags.SCROLLBAR

const val CUSTOM_SCROLL_BAR_HEIGHT = 20f
const val SCROLL_BAR_BACKGROUND_COLOR = 0xFF004AD3

@Composable
fun CustomScrollableListComponent(
    modifier: Modifier = Modifier,
    customScrollableListComponentData: CustomScrollableListComponentData,
) {
    val listState = rememberLazyListState()
    var scrollProgress by remember { mutableFloatStateOf(0f) }
    var scrollbarHeight by remember { mutableFloatStateOf(CUSTOM_SCROLL_BAR_HEIGHT) }

    LaunchedEffect(listState) {
        snapshotFlow {
            val layoutInfo = listState.layoutInfo
            val visible = layoutInfo.visibleItemsInfo
            val total = layoutInfo.totalItemsCount
            if (total > 0 && visible.isNotEmpty()) {
                val progress =
                    visible.first().index.toFloat() /
                        (total - visible.size).coerceAtLeast(1)
                val visibleFraction = visible.size.toFloat() / total
                progress to visibleFraction
            } else {
                0f to 1f
            }
        }.collect { (progress, fraction) ->
            scrollProgress = progress
            scrollbarHeight = fraction
        }
    }

    Box(modifier = modifier) {
        LazyColumn(
            state = listState,
            modifier =
                Modifier
                    .height(customScrollableListComponentData.contentHeight)
                    .fillMaxWidth()
                    .testTag(LAZY_COLUMN),
        ) { customScrollableListComponentData.content(this) }

        Box(
            modifier =
                Modifier
                    .align(Alignment.CenterEnd)
                    .height(customScrollableListComponentData.contentHeight),
        ) {
            val thumbHeight = customScrollableListComponentData.contentHeight * scrollbarHeight
            val yOffset =
                scrollProgress * (customScrollableListComponentData.contentHeight - thumbHeight)
            Box(
                modifier =
                    Modifier
                        .align(Alignment.TopEnd)
                        .height(thumbHeight)
                        .offset(y = yOffset)
                        .width(17.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(SCROLL_BAR_BACKGROUND_COLOR))
                        .testTag(SCROLLBAR),
            )
        }
    }
}

data class CustomScrollableListComponentData(
    val contentHeight: Dp,
    val content: LazyListScope.() -> Unit,
)

object CustomScrollableListComponentTags {
    private const val P = "CUSTOM_SCROLLABLE_LIST_ITEM_COMPONENT_"
    private const val S = "_TEST_TAG"
    const val LAZY_COLUMN = "${P}LAZY_COLUMN$S"
    const val SCROLLBAR = "${P}SCROLLBAR$S"
}
