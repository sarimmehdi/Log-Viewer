package com.sarim.header_presentation.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.Modifier
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class CustomRadioButtonComponentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `radio button shows on state correctly`() {
        val data = CustomRadioButtonComponentData(on = true)

        composeTestRule.setContent {
            CustomRadioButtonComponent(
                data = data,
                modifier = Modifier,
            )
        }

        composeTestRule
            .onNodeWithTag(CustomRadioButtonComponentTags.DATA_ON)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(CustomRadioButtonComponentTags.DATA_OFF)
            .assertDoesNotExist()
    }

    @Test
    fun `radio button shows off state correctly`() {
        val data = CustomRadioButtonComponentData(on = false)

        composeTestRule.setContent {
            CustomRadioButtonComponent(
                data = data,
                modifier = Modifier,
            )
        }

        composeTestRule
            .onNodeWithTag(CustomRadioButtonComponentTags.DATA_OFF)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag(CustomRadioButtonComponentTags.DATA_ON)
            .assertDoesNotExist()
    }
}
