package com.sarim.ui.component

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.sarim.ui.component.SearchboxComponentTags.TEXT_FIELD
import com.sarim.ui.component.SearchboxComponentTags.iconTestTag
import com.sarim.utils.R
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class SearchboxComponentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var data: SearchboxComponentData
    lateinit var onValueChange: (String) -> Unit

    @Before
    fun setUp() {
        onValueChange = mockk<(String) -> Unit>(relaxed = true)
        data =
            SearchboxComponentData(
                placeholderText = "Search",
                iconDescription = "icon",
                backgroundColor = Color.Black,
                onValueChange = onValueChange,
            )
    }

    @Test
    fun `onValueChange is called when typing`() {
        composeTestRule.setContent {
            SearchboxComponent(
                data = data,
            )
        }
        composeTestRule.onNodeWithTag(TEXT_FIELD).performTextInput("abc")
        verify { onValueChange.invoke("abc") }
    }

    @Test
    fun `typed text appears in the text field`() {
        composeTestRule.setContent {
            SearchboxComponent(
                data = data,
            )
        }
        composeTestRule.onNodeWithTag(TEXT_FIELD).performTextInput("hello")
        composeTestRule.onNodeWithTag(TEXT_FIELD).assertTextContains("hello")
    }

    @Test
    fun `placeholder is visible initially and disappears when typing`() {
        composeTestRule.setContent {
            SearchboxComponent(data = data)
        }

        composeTestRule.onNodeWithText(data.placeholderText).assertIsDisplayed()
        composeTestRule
            .onNodeWithTag(TEXT_FIELD)
            .performTextInput("Hello")
        composeTestRule.onNodeWithText(data.placeholderText).assertIsNotDisplayed()
        composeTestRule.onNodeWithText("Hello").assertIsDisplayed()
    }

    @Test
    fun `shows magnifying glass when text empty and clears text on icon click`() {
        composeTestRule.setContent {
            SearchboxComponent(data = data)
        }

        composeTestRule
            .onNodeWithTag(
                iconTestTag(R.drawable.magnifying_glass),
                useUnmergedTree = true,
            ).assertIsDisplayed()
        composeTestRule
            .onNodeWithTag(
                iconTestTag(R.drawable.side_bar_close_icon),
                useUnmergedTree = true,
            ).assertIsNotDisplayed()
        composeTestRule
            .onNodeWithTag(TEXT_FIELD)
            .performTextInput("Hello")
        composeTestRule
            .onNodeWithTag(
                iconTestTag(R.drawable.magnifying_glass),
                useUnmergedTree = true,
            ).assertIsNotDisplayed()
        composeTestRule
            .onNodeWithTag(
                iconTestTag(R.drawable.side_bar_close_icon),
                useUnmergedTree = true,
            ).assertIsDisplayed()
        composeTestRule
            .onNodeWithTag(
                iconTestTag(R.drawable.side_bar_close_icon),
                useUnmergedTree = true,
            ).performClick()
        verify { onValueChange.invoke("") }
    }
}
