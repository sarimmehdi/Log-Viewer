package com.sarim.header_presentation

import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.sarim.header_presentation.HeaderScreenTags.P
import com.sarim.header_presentation.HeaderScreenTags.S
import com.sarim.header_presentation.HeaderScreenTags.dropDownTestTag
import com.sarim.maincontent_domain.model.LogType
import kotlinx.collections.immutable.persistentListOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class HeaderScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `class drop down shows test tag`() {
        val classFilters =
            persistentListOf(
                ClassFilter("Class A", true),
                ClassFilter("Class B", false),
            )

        val data =
            HeaderScreenData(
                dropDownType = DropDownType.CLASS_DROP_DOWN,
                classFilters = classFilters,
                functionFilters = persistentListOf(),
                logTypeFilters = persistentListOf(),
            )

        composeTestRule.setContent {
            HeaderScreen(
                data = data,
                onEvent = {},
            )
        }

        composeTestRule
            .onNodeWithTag(dropDownTestTag(DropDownType.CLASS_DROP_DOWN))
            .assertIsDisplayed()
    }

    @Test
    fun `function drop down shows test tag`() {
        val functionFilters =
            persistentListOf(
                FunctionFilter("Function X", true),
                FunctionFilter("Function Y", false),
            )

        val data =
            HeaderScreenData(
                dropDownType = DropDownType.FUNCTION_DROP_DOWN,
                classFilters = persistentListOf(),
                functionFilters = functionFilters,
                logTypeFilters = persistentListOf(),
            )

        composeTestRule.setContent {
            HeaderScreen(
                data = data,
                onEvent = {},
            )
        }

        composeTestRule
            .onNodeWithTag(dropDownTestTag(DropDownType.FUNCTION_DROP_DOWN))
            .assertIsDisplayed()
    }

    @Test
    fun `log type drop down shows test tag`() {
        val logTypeFilters =
            persistentListOf(
                LogTypeFilter(LogType.ERROR, true),
                LogTypeFilter(LogType.INFO, false),
            )

        val data =
            HeaderScreenData(
                dropDownType = DropDownType.LOG_TYPE_DROP_DOWN,
                classFilters = persistentListOf(),
                functionFilters = persistentListOf(),
                logTypeFilters = logTypeFilters,
            )

        composeTestRule.setContent {
            HeaderScreen(
                data = data,
                onEvent = {},
            )
        }

        composeTestRule
            .onNodeWithTag(dropDownTestTag(DropDownType.LOG_TYPE_DROP_DOWN))
            .assertExists()
    }

    @Test
    fun `no drop down nodes exist when type is NONE`() {
        val data =
            HeaderScreenData(
                dropDownType = DropDownType.NONE,
                classFilters = persistentListOf(),
                functionFilters = persistentListOf(),
                logTypeFilters = persistentListOf(),
            )

        composeTestRule.setContent {
            HeaderScreen(data = data, onEvent = {})
        }

        val dropDownTagRegex = Regex("${P}DROP_DOWN_.*$S")
        val matcher =
            SemanticsMatcher("Test tag matches regex $dropDownTagRegex") { node ->
                val tag = node.config.getOrNull(SemanticsProperties.TestTag)
                tag != null && dropDownTagRegex.matches(tag)
            }
        composeTestRule.onAllNodes(matcher).assertCountEquals(0)
    }
}
