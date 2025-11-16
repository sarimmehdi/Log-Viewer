package com.sarim.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

const val SELECTED_BACKGROUND_COLOR = 0xFFD9D9D9

@Composable
fun SidebarListItemComponent(
    data: SidebarListItemComponentData,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .width(425.dp)
                .height(78.dp)
                .then(
                    if (data.selected) {
                        Modifier.background(Color(SELECTED_BACKGROUND_COLOR).copy(alpha = 0.1f))
                    } else {
                        Modifier
                    },
                ).clickable { onClick() },
    ) {
        if (data.selected) {
            Box(
                modifier =
                    Modifier
                        .width(20.dp)
                        .height(78.dp)
                        .background(Color.White),
            )
        }
        Column {
            Text(
                text = data.heading,
                modifier =
                    Modifier
                        .padding(
                            start =
                                if (data.selected) {
                                    13.dp
                                } else {
                                    33.dp
                                },
                            top = 5.dp,
                        ),
                style = MaterialTheme.typography.labelMedium,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(
                modifier =
                    Modifier
                        .size(5.dp),
            )
            Text(
                text = data.subHeading,
                modifier =
                    Modifier
                        .padding(
                            start =
                                if (data.selected) {
                                    13.dp
                                } else {
                                    33.dp
                                },
                        ),
                style = MaterialTheme.typography.labelMedium,
                color = Color.White.copy(alpha = 0.5f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

data class SidebarListItemComponentData(
    val heading: String,
    val subHeading: String,
    val selected: Boolean,
)
