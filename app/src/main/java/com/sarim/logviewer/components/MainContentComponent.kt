package com.sarim.logviewer.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.utils.component.CustomScrollableListComponent
import com.sarim.utils.component.CustomScrollableListComponentData
import com.sarim.utils.component.HorizontalDividerComponent
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Composable
fun MainContentComponent(
    modifier: Modifier = Modifier,
    data: MainContentComponentData = MainContentComponentData(),
) {
    Column(
        modifier =
            modifier
                .clip(RoundedCornerShape(10.dp))
                .width(819.dp)
                .height(645.dp)
                .background(Color(0xFF03111B)),
    ) {
        MainContentListItemComponent(
            data =
                MainContentListItemComponentData(
                    message = "Message",
                    className = "Class",
                    function = "Function",
                    line = Line.Text(value = "Line"),
                    level = Level.Text(value = "Level"),
                    textSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                ),
        )
        HorizontalDividerComponent()
        CustomScrollableListComponent(
            customScrollableListComponentData =
                CustomScrollableListComponentData(
                    contentHeight = 584.dp,
                ) {
                    items(data.logObjects.size) { index ->
                        MainContentListItemComponent(
                            data = data.logObjects[index],
                        )
                        HorizontalDividerComponent()
                    }
                },
        )
    }
}

data class MainContentComponentData(
    val logObjects: ImmutableList<MainContentListItemComponentData> = persistentListOf(),
)

@Preview(
    device = PIXEL_TABLET,
)
@Composable
fun MainContentComponentPreview() {
    val baseLogItems =
        listOf(
            MainContentListItemComponentData(
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                function = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                line = Line.Integer(value = 9999),
                level =
                    Level.Content(
                        composable = {
                            LevelComponent(
                                data =
                                    LevelComponentData(
                                        name = "ERROR",
                                        color = Color(0xFFD30000),
                                    ),
                                modifier =
                                    Modifier
                                        .padding(
                                            start = 57.dp,
                                            end = 73.dp,
                                        ),
                            )
                        },
                    ),
                textSize = 12.sp,
                fontWeight = FontWeight.Bold,
            ),
            MainContentListItemComponentData(
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                function = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                line = Line.Integer(value = 9999),
                level =
                    Level.Content(
                        composable = {
                            LevelComponent(
                                data =
                                    LevelComponentData(
                                        name = "DEBUG",
                                        color = Color(0xFF004AD3),
                                    ),
                                modifier =
                                    Modifier
                                        .padding(
                                            start = 56.dp,
                                            end = 74.dp,
                                        ),
                            )
                        },
                    ),
                textSize = 12.sp,
                fontWeight = FontWeight.Bold,
            ),
            MainContentListItemComponentData(
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                function = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                line = Line.Integer(value = 9999),
                level =
                    Level.Content(
                        composable = {
                            LevelComponent(
                                data =
                                    LevelComponentData(
                                        name = "INFO",
                                        color = Color(0xFF3FD300),
                                    ),
                                modifier =
                                    Modifier
                                        .padding(
                                            start = 57.dp,
                                            end = 73.dp,
                                        ),
                            )
                        },
                    ),
                textSize = 12.sp,
                fontWeight = FontWeight.Bold,
            ),
            MainContentListItemComponentData(
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                function = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                line = Line.Integer(value = 9999),
                level =
                    Level.Content(
                        composable = {
                            LevelComponent(
                                data =
                                    LevelComponentData(
                                        name = "WARN",
                                        color = Color(0xFFD36600),
                                    ),
                                modifier =
                                    Modifier
                                        .padding(
                                            start = 55.dp,
                                            end = 75.dp,
                                        ),
                            )
                        },
                    ),
                textSize = 12.sp,
                fontWeight = FontWeight.Bold,
            ),
            MainContentListItemComponentData(
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                function = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                line = Line.Integer(value = 9999),
                level =
                    Level.Content(
                        composable = {
                            LevelComponent(
                                data =
                                    LevelComponentData(
                                        name = "CRITICAL",
                                        color = Color(0xFFFF0004),
                                    ),
                                modifier =
                                    Modifier
                                        .padding(
                                            start = 55.dp,
                                            end = 66.dp,
                                        ),
                            )
                        },
                    ),
                textSize = 12.sp,
                fontWeight = FontWeight.Bold,
            ),
        )
    MainContentComponent(
        data =
            MainContentComponentData(
                logObjects = List(100) { index -> baseLogItems[index % baseLogItems.size] }.toImmutableList(),
            ),
    )
}
