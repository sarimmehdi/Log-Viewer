package com.sarim.footer_presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.sarim.footer_presentation.FooterScreenTags.nextDoubleArrowButtonTestTag
import com.sarim.footer_presentation.FooterScreenTags.nextSingleArrowButtonTestTag
import com.sarim.footer_presentation.FooterScreenTags.prevDoubleArrowButtonTestTag
import com.sarim.footer_presentation.FooterScreenTags.prevSingleArrowButtonTestTag
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner

@RunWith(ParameterizedRobolectricTestRunner::class)
class FooterScreenTest(
    private val canGoToNextPage: Boolean,
    private val canGoToPreviousPage: Boolean,
) {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `arrow buttons have correct test tags`() {
        val data =
            FooterScreenData(
                numberFirstLogOnPage = 1,
                numberLastLogOnPage = 10,
                totalLogs = 100,
                currentPageNumber = 1,
                totalPages = 10,
                canGoToNextPage = canGoToNextPage,
                canGoToPreviousPage = canGoToPreviousPage,
            )

        composeTestRule.setContent {
            FooterScreen(
                data = data,
                onEvent = {},
            )
        }

        val prevAlpha = if (canGoToPreviousPage) CLICKABLE_ALPHA else UNCLICKABLE_ALPHA
        val nextAlpha = if (canGoToNextPage) CLICKABLE_ALPHA else UNCLICKABLE_ALPHA

        composeTestRule
            .onNodeWithTag(prevDoubleArrowButtonTestTag(prevAlpha))
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithTag(prevSingleArrowButtonTestTag(prevAlpha))
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithTag(nextSingleArrowButtonTestTag(nextAlpha))
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithTag(nextDoubleArrowButtonTestTag(nextAlpha))
            .assertIsDisplayed()
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "Next={0}, Prev={1}")
        @Suppress("unused")
        fun data() =
            listOf(
                arrayOf(true, true),
                arrayOf(true, false),
                arrayOf(false, true),
                arrayOf(false, false),
            )
    }
}
