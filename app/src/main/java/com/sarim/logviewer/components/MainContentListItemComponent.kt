package com.sarim.logviewer.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.logviewer.R

@Composable
fun MainContentListItemComponent(
    data: MainContentListItemComponentData,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .height(61.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier =
                Modifier
                    .padding(
                        start = 32.dp,
                    ).width(247.dp),
            fontWeight = data.fontWeight,
            fontSize = data.textSize,
            fontFamily =
                FontFamily(
                    Font(R.font.inter_24_regular, FontWeight.Normal),
                    Font(R.font.inter_24_medium, FontWeight.Medium),
                    Font(R.font.inter_24_bold, FontWeight.Bold),
                ),
            text = data.message,
            color = Color.White.copy(alpha = 0.7f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        VerticalDividerComponent(
            modifier =
                Modifier
                    .height(61.dp),
        )
        Text(
            modifier =
                Modifier
                    .padding(
                        start = 21.dp,
                    ).width(89.dp),
            fontWeight = data.fontWeight,
            fontSize = data.textSize,
            fontFamily =
                FontFamily(
                    Font(R.font.inter_24_regular, FontWeight.Normal),
                    Font(R.font.inter_24_medium, FontWeight.Medium),
                    Font(R.font.inter_24_bold, FontWeight.Bold),
                ),
            text = data.className,
            color = Color.White.copy(alpha = 0.7f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        VerticalDividerComponent(
            modifier =
                Modifier
                    .height(61.dp),
        )
        Text(
            modifier =
                Modifier
                    .padding(
                        start = 25.dp,
                    ).width(102.dp),
            fontWeight = data.fontWeight,
            fontSize = data.textSize,
            fontFamily =
                FontFamily(
                    Font(R.font.inter_24_regular, FontWeight.Normal),
                    Font(R.font.inter_24_medium, FontWeight.Medium),
                    Font(R.font.inter_24_bold, FontWeight.Bold),
                ),
            text = data.function,
            color = Color.White.copy(alpha = 0.7f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        VerticalDividerComponent(
            modifier =
                Modifier
                    .height(61.dp),
        )
        Text(
            modifier =
                Modifier
                    .padding(
                        start = 36.dp,
                    ).width(68.dp),
            fontWeight = data.fontWeight,
            fontSize = data.textSize,
            fontFamily =
                FontFamily(
                    Font(R.font.inter_24_regular, FontWeight.Normal),
                    Font(R.font.inter_24_medium, FontWeight.Medium),
                    Font(R.font.inter_24_bold, FontWeight.Bold),
                ),
            text = data.line.toString(),
            color = Color.White.copy(alpha = 0.7f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        VerticalDividerComponent(
            modifier =
                Modifier
                    .height(61.dp),
        )
        when (data.level) {
            is Level.Text -> {
                Text(
                    modifier =
                        Modifier
                            .padding(
                                start = 65.dp,
                                end = 80.dp,
                            ),
                    fontWeight = data.fontWeight,
                    fontSize = data.textSize,
                    fontFamily =
                        FontFamily(
                            Font(R.font.inter_24_regular, FontWeight.Normal),
                            Font(R.font.inter_24_medium, FontWeight.Medium),
                            Font(R.font.inter_24_bold, FontWeight.Bold),
                        ),
                    text = "Level",
                    color = Color.White.copy(alpha = 0.7f),
                )
            }
            is Level.Content -> {
                data.level.composable()
            }
        }
    }
}

sealed class Level {
    data class Text(
        val value: String = "",
    ) : Level()

    data class Content(
        val composable: @Composable () -> Unit = {},
    ) : Level()
}

sealed class Line {
    class Text(
        val value: String = "",
    ) : Line()

    class Integer(
        val value: Int = 9999,
    ) : Line()

    override fun toString(): String =
        when (this) {
            is Text -> value
            is Integer -> value.toString()
        }
}

data class MainContentListItemComponentData(
    val message: String,
    val className: String,
    val function: String,
    val line: Line,
    val level: Level,
    val textSize: TextUnit,
    val fontWeight: FontWeight,
)

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
                function = "Function",
                line = Line.Text(value = "Line"),
                level = Level.Text(value = "Level"),
                textSize = 16.sp,
                fontWeight = FontWeight.Normal,
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
}
