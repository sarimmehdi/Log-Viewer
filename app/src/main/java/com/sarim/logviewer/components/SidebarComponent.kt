package com.sarim.logviewer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SidebarComponent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(425.dp)
            .height(800.dp)
            .background(Color(0xFF03111B))
    ) {
        SidebarHeaderComponent()
        SidebarHorizontalDividerComponent()
        SidebarSearchboxComponent(
            data = SidebarSearchboxComponentData(
                placeholderText = "Search dates",
                iconDescription = "Icon to search dates",
            ),
            modifier = Modifier
                .padding(top = 18.dp, start = 33.dp)
        )
        SidebarHorizontalDividerComponent(
            modifier = Modifier
                .padding(top = 20.dp)
        )
        Spacer(
            modifier = Modifier.size(9.dp)
        )
        List(3) { index ->
            SidebarListItemComponentData(
                heading = "Date 1",
                subHeading = "9 sessions",
                selected = index % 2 == 0
            )
        }.forEach { data ->
            SidebarListItemComponent(
                data = data
            )
        }
        SidebarHorizontalDividerComponent(
            modifier = Modifier
                .padding(top = 18.dp)
        )
        SidebarSearchboxComponent(
            data = SidebarSearchboxComponentData(
                placeholderText = "Search sessions",
                iconDescription = "Icon to search sessions",
            ),
            modifier = Modifier
                .padding(top = 18.dp, start = 33.dp)
        )
        SidebarHorizontalDividerComponent(
            modifier = Modifier
                .padding(top = 18.dp)
        )
        Spacer(
            modifier = Modifier.size(22.dp)
        )
        List(3) { index ->
            SidebarListItemComponentData(
                heading = "Session 1",
                subHeading = "10 messages",
                selected = index % 2 == 0
            )
        }.forEach { data ->
            SidebarListItemComponent(
                data = data
            )
        }
    }
}

@Preview
@Composable
fun SidebarComponentPreview() {
    SidebarComponent()
}