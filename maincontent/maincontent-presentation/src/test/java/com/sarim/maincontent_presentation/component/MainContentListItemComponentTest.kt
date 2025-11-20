package com.sarim.maincontent_presentation.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sarim.utils.test.printToLog
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(qualifiers = "w1080dp-h1920dp")
@RunWith(RobolectricTestRunner::class)
class MainContentListItemComponentTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `level text displays heading with correct tag`() {
        val data =
            MainContentListItemComponentData(
                message = "Message",
                className = "Class",
                functionName = "Function",
                lineNumber = Line.Text(value = "Line"),
                level = Level.Text(value = "Level"),
                textSize = 16.sp,
                fontWeight = FontWeight.Normal,
            )

        composeRule.setContent {
            MainContentListItemComponent(data = data)
        }
        composeRule.onRoot().printToLog()

        composeRule
            .onNodeWithText("Level")
            .assertIsDisplayed()
    }

    @Test
    fun `level content executes its composable`() {
        @Composable
        fun FakeComposable() {
            Text(
                text = "FAKE",
                modifier = Modifier.testTag("FAKE_TAG"),
            )
        }

        val content =
            Level.Content(
                composable = { FakeComposable() },
            )

        val data =
            MainContentListItemComponentData(
                message = "msg",
                className = "cls",
                functionName = "fn",
                lineNumber = Line.Integer(10),
                level = content,
                textSize = 14.sp,
                fontWeight = FontWeight.Normal,
            )

        composeRule.setContent {
            MainContentListItemComponent(
                data = data,
            )
        }

        composeRule
            .onNodeWithTag("FAKE_TAG")
            .assertIsDisplayed()
    }
}
