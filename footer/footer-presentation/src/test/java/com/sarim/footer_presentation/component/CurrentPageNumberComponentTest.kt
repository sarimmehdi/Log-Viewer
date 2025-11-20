package com.sarim.footer_presentation.component

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.sarim.footer_presentation.component.CurrentPageNumberComponentTags.TEXT_FIELD
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class CurrentPageNumberComponentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var data: CurrentPageNumberComponentData
    lateinit var onDone: (Int) -> Unit

    @Before
    fun setUp() {
        onDone = mockk(relaxed = true)
        data = CurrentPageNumberComponentData(currentPageNumber = 3)
    }

    @Test
    fun `field shows initial two digits`() {
        composeTestRule.setContent {
            CurrentPageNumberComponent(
                modifier = Modifier.testTag(TEXT_FIELD),
                data = data,
                onDone = onDone,
            )
        }

        composeTestRule.onNodeWithTag(TEXT_FIELD).assertTextEquals("03")
    }

    @Test
    fun `field shows number greater than 10`() {
        composeTestRule.setContent {
            CurrentPageNumberComponent(
                modifier = Modifier.testTag(TEXT_FIELD),
                data = data.copy(currentPageNumber = 15),
                onDone = onDone,
            )
        }

        composeTestRule.onNodeWithTag(TEXT_FIELD).assertTextEquals("15")
    }

    @Test
    fun `field shows number greater than 100`() {
        composeTestRule.setContent {
            CurrentPageNumberComponent(
                modifier = Modifier.testTag(TEXT_FIELD),
                data = data.copy(currentPageNumber = 541),
                onDone = onDone,
            )
        }

        composeTestRule.onNodeWithTag(TEXT_FIELD).assertTextEquals("541")
    }

    @Test
    fun `typing only allows digits`() {
        composeTestRule.setContent {
            CurrentPageNumberComponent(
                modifier = Modifier.testTag(TEXT_FIELD),
                data = data,
                onDone = onDone,
            )
        }

        composeTestRule.onNodeWithTag(TEXT_FIELD).performTextClearance()
        composeTestRule.onNodeWithTag(TEXT_FIELD).performTextInput("a1b23ce#54")
        composeTestRule.onNodeWithTag(TEXT_FIELD).assertTextEquals("12354")
    }

    @Test
    fun `done action calls onDone with parsed Int`() {
        composeTestRule.setContent {
            CurrentPageNumberComponent(
                modifier = Modifier.testTag(TEXT_FIELD),
                data = data,
                onDone = onDone,
            )
        }

        composeTestRule.onNodeWithTag(TEXT_FIELD).performTextClearance()
        composeTestRule.onNodeWithTag(TEXT_FIELD).performTextInput("09")
        composeTestRule.onNodeWithTag(TEXT_FIELD).performImeAction()

        verify { onDone.invoke(9) }
    }

    @Test
    fun `done action calls onDone with parsed Int greater than 10`() {
        composeTestRule.setContent {
            CurrentPageNumberComponent(
                modifier = Modifier.testTag(TEXT_FIELD),
                data = data,
                onDone = onDone,
            )
        }

        composeTestRule.onNodeWithTag(TEXT_FIELD).performTextClearance()
        composeTestRule.onNodeWithTag(TEXT_FIELD).performTextInput("54")
        composeTestRule.onNodeWithTag(TEXT_FIELD).performImeAction()

        verify { onDone.invoke(54) }
    }

    @Test
    fun `done action calls onDone with parsed Int greater than 100`() {
        composeTestRule.setContent {
            CurrentPageNumberComponent(
                modifier = Modifier.testTag(TEXT_FIELD),
                data = data,
                onDone = onDone,
            )
        }

        composeTestRule.onNodeWithTag(TEXT_FIELD).performTextClearance()
        composeTestRule.onNodeWithTag(TEXT_FIELD).performTextInput("368")
        composeTestRule.onNodeWithTag(TEXT_FIELD).performImeAction()

        verify { onDone.invoke(368) }
    }
}
