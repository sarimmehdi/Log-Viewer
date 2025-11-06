package com.sarim.maincontent_presentation.debug.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import com.sarim.maincontent_presentation.component.Line
import com.sarim.maincontent_presentation.component.MainContentListItemComponentData
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.maincontent_presentation.component.ERROR_COLOR
import com.sarim.maincontent_presentation.component.DEBUG_COLOR
import com.sarim.maincontent_presentation.component.INFO_COLOR
import com.sarim.maincontent_presentation.component.WARN_COLOR
import com.sarim.maincontent_presentation.component.CRITICAL_COLOR
import com.sarim.maincontent_presentation.component.Level
import com.sarim.maincontent_presentation.component.LevelComponent
import com.sarim.maincontent_presentation.component.LevelComponentData
import com.sarim.maincontent_presentation.component.MainContentListItemComponent

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun SearchboxComponentPreview(
    @PreviewParameter(MainContentListItemComponentDataParameterProvider::class) data: MainContentListItemComponentData,
) {
    MainContentListItemComponent(
        data = data,
    )
}

class MainContentListItemComponentDataParameterProvider : PreviewParameterProvider<MainContentListItemComponentData> {
    override val values =
        sequenceOf(
            MainContentListItemComponentData(
                message = "Message",
                className = "Class",
                functionName = "Function",
                lineNumber = Line.Text(value = "Line"),
                level = Level.Text(value = "Level"),
                textSize = 16.sp,
                fontWeight = FontWeight.Normal,
            ),
            MainContentListItemComponentData(
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                functionName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                lineNumber = Line.Integer(value = 9999),
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
                functionName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                lineNumber = Line.Integer(value = 9999),
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
                functionName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                lineNumber = Line.Integer(value = 9999),
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
                functionName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                lineNumber = Line.Integer(value = 9999),
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
                functionName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                lineNumber = Line.Integer(value = 9999),
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
}
