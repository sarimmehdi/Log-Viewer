package com.sarim.maincontent_presentation.debug

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import com.sarim.maincontent_presentation.component.Line
import com.sarim.maincontent_presentation.component.MainContentListItemComponentData
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.maincontent_presentation.MainContentScreen
import com.sarim.maincontent_presentation.MainContentScreenData
import com.sarim.maincontent_presentation.component.Level
import com.sarim.maincontent_presentation.component.LevelComponent
import com.sarim.maincontent_presentation.component.LevelComponentData
import kotlinx.collections.immutable.toImmutableList

const val ERROR_COLOR = 0xFFD30000
const val DEBUG_COLOR = 0xFF004AD3
const val INFO_COLOR = 0xFF3FD300
const val WARN_COLOR = 0xFFD36600
const val CRITICAL_COLOR = 0xFFFF0004
const val LOG_OBJECTS = 100

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
                                        color = Color(ERROR_COLOR),
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
                                        color = Color(DEBUG_COLOR),
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
                                        color = Color(INFO_COLOR),
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
                                        color = Color(WARN_COLOR),
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
                                        color = Color(CRITICAL_COLOR),
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
    MainContentScreen(
        data =
            MainContentScreenData(
                logObjects = List(LOG_OBJECTS) { index -> baseLogItems[index % baseLogItems.size] }.toImmutableList(),
            ),
    )
}
