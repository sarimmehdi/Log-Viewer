package com.sarim.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.getBoundsInRoot
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToKey
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.common.truth.Truth.assertThat
import com.sarim.ui.component.CustomScrollableListComponentTags.LAZY_COLUMN
import com.sarim.ui.component.CustomScrollableListComponentTags.SCROLLBAR
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.collections.forEach

@RunWith(RobolectricTestRunner::class)
internal class CustomScrollableListComponentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `all items in view and out of view as you scroll up and down`() {
        val totalItems = 50
        val viewPortHeight = 500.dp
        val itemHeight = 50.dp

        fun testTag(idx: Int) = "CUSTOM_SCROLLABLE_LIST_COMPONENT_TEST_TAG_$idx"

        composeTestRule.setContent {
            CustomScrollableListComponent(
                customScrollableListComponentData =
                    CustomScrollableListComponentData(
                        contentHeight = viewPortHeight,
                    ) {
                        items(
                            count = totalItems,
                            key = { index -> testTag(index) },
                        ) {
                            Box(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .height(itemHeight)
                                        .background(Color.White)
                                        .testTag(testTag(it)),
                            )
                        }
                    },
            )
        }

        val visibleItemsCount = (viewPortHeight.value / itemHeight.value).toInt()
        val allIndices = 0 until totalItems
        val batches = allIndices.toList().windowed(size = visibleItemsCount, step = 1)

        fun Dp.toPx() = this.value * composeTestRule.density.density
        batches.shuffled().forEach { batch ->
            composeTestRule
                .onNodeWithTag(LAZY_COLUMN)
                .performScrollToKey(testTag(batch.first()))

            batch.forEach { index ->
                composeTestRule
                    .onNodeWithTag(testTag(index), useUnmergedTree = true)
                    .assertIsDisplayed()
            }

            allIndices.forEach { index ->
                val node = composeTestRule.onNodeWithTag(testTag(index), useUnmergedTree = true)
                if (index in batch) {
                    node.assertIsDisplayed()
                } else {
                    node.assertIsNotDisplayed()
                }
            }

            val scrollbarBounds =
                composeTestRule
                    .onNodeWithTag(SCROLLBAR)
                    .getBoundsInRoot()

            val actualYOffsetPx = scrollbarBounds.top.toPx()

            val expectedScrollProgress =
                batch.first().toFloat() / (totalItems - visibleItemsCount)

            val contentHeightPx = viewPortHeight.toPx()
            val visibleFraction = visibleItemsCount.toFloat() / totalItems
            val thumbHeightPx = contentHeightPx * visibleFraction
            val maxTravelPx = contentHeightPx - thumbHeightPx

            val expectedYOffsetPx = expectedScrollProgress * maxTravelPx
            val epsilon = 0.5f
            assertThat(actualYOffsetPx)
                .isWithin(epsilon)
                .of(expectedYOffsetPx)
        }
    }
}
