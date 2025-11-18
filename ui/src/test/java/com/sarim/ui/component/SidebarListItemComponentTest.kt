package com.sarim.ui.component

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class SidebarListItemComponentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `selected item is displayed`() {
        val data =
            SidebarListItemComponentData(
                heading = "A",
                subHeading = "B",
                selected = true,
            )

        composeTestRule.setContent {
            SidebarListItemComponent(
                data = data,
                onClick = {},
                modifier = Modifier.semantics { testTagsAsResourceId = true },
            )
        }

        composeTestRule
            .onNodeWithTag(SidebarListItemComponentTags.SELECTED_BACKGROUND, useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithTag(
                SidebarListItemComponentTags.NOT_SELECTED_BACKGROUND,
                useUnmergedTree = true,
            ).assertIsNotDisplayed()
        composeTestRule
            .onNodeWithTag(SidebarListItemComponentTags.SELECTED_BOX, useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun `not selected item is displayed`() {
        val data =
            SidebarListItemComponentData(
                heading = "A",
                subHeading = "B",
                selected = false,
            )

        composeTestRule.setContent {
            SidebarListItemComponent(
                data = data,
                onClick = {},
                modifier = Modifier.semantics { testTagsAsResourceId = true },
            )
        }

        composeTestRule
            .onNodeWithTag(SidebarListItemComponentTags.SELECTED_BACKGROUND, useUnmergedTree = true)
            .assertIsNotDisplayed()
        composeTestRule
            .onNodeWithTag(
                SidebarListItemComponentTags.NOT_SELECTED_BACKGROUND,
                useUnmergedTree = true,
            ).assertIsDisplayed()
        composeTestRule
            .onNodeWithTag(SidebarListItemComponentTags.SELECTED_BOX, useUnmergedTree = true)
            .assertIsNotDisplayed()
    }
}
