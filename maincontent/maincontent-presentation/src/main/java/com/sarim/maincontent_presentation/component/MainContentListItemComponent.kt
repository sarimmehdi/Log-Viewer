package com.sarim.maincontent_presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.sarim.maincontent_domain.model.LogMessage.LogType
import com.sarim.maincontent_presentation.R
import com.sarim.ui.component.VerticalDividerComponent
import com.sarim.ui.theme.bodyFontFamily

const val ERROR_COLOR = 0xFFD30000
const val DEBUG_COLOR = 0xFF004AD3
const val INFO_COLOR = 0xFF3FD300
const val WARN_COLOR = 0xFFD36600
const val CRITICAL_COLOR = 0xFFFF0004

@Composable
fun MainContentListItemComponent(
    data: MainContentListItemComponentData,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.height(61.dp),
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
            fontFamily = bodyFontFamily,
            text = data.message,
            color = Color.White.copy(alpha = 0.7f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        VerticalDividerComponent(
            modifier = Modifier.height(61.dp),
        )
        Text(
            modifier =
                Modifier
                    .padding(
                        start = 21.dp,
                    ).width(89.dp),
            fontWeight = data.fontWeight,
            fontSize = data.textSize,
            fontFamily = bodyFontFamily,
            text = data.className,
            color = Color.White.copy(alpha = 0.7f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        VerticalDividerComponent(
            modifier = Modifier.height(61.dp),
        )
        Text(
            modifier =
                Modifier
                    .padding(
                        start = 25.dp,
                    ).width(102.dp),
            fontWeight = data.fontWeight,
            fontSize = data.textSize,
            fontFamily = bodyFontFamily,
            text = data.functionName,
            color = Color.White.copy(alpha = 0.7f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        VerticalDividerComponent(
            modifier = Modifier.height(61.dp),
        )
        Text(
            modifier =
                Modifier
                    .padding(
                        start = 36.dp,
                    ).width(68.dp),
            fontWeight = data.fontWeight,
            fontSize = data.textSize,
            fontFamily = bodyFontFamily,
            text = data.lineNumber.toString(),
            color = Color.White.copy(alpha = 0.7f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        VerticalDividerComponent(
            modifier = Modifier.height(61.dp),
        )
        when (data.level) {
            is MainContentListItemComponentData.Level.Text -> {
                Text(
                    modifier =
                        Modifier
                            .padding(
                                start = 65.dp,
                                end = 80.dp,
                            ),
                    fontWeight = data.fontWeight,
                    fontSize = data.textSize,
                    fontFamily = bodyFontFamily,
                    text = data.level.value,
                    color = Color.White.copy(alpha = 0.7f),
                )
            }

            is MainContentListItemComponentData.Level.Content -> {
                data.level.composable()
            }
        }
    }
}

data class MainContentListItemComponentData(
    val message: String,
    val className: String,
    val functionName: String,
    val lineNumber: Line,
    val level: Level,
    val textSize: TextUnit,
    val fontWeight: FontWeight,
) {
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

    sealed class Level {
        data class Text(
            val value: String = "",
        ) : Level()

        data class Content(
            val composable: @Composable () -> Unit = {},
        ) : Level() {
            companion object {
                @Suppress("LongMethod")
                fun fromLogType(logType: LogType) =
                    when (logType) {
                        LogType.ERROR ->
                            Content(
                                composable = {
                                    LevelComponent(
                                        data =
                                            LevelComponentData(
                                                name = stringResource(R.string.error),
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
                            )

                        LogType.DEBUG ->
                            Content(
                                composable = {
                                    LevelComponent(
                                        data =
                                            LevelComponentData(
                                                name = stringResource(R.string.debug),
                                                color = Color(DEBUG_COLOR),
                                            ),
                                        modifier =
                                            Modifier
                                                .padding(
                                                    start = 57.dp,
                                                    end = 73.dp,
                                                ),
                                    )
                                },
                            )

                        LogType.INFO ->
                            Content(
                                composable = {
                                    LevelComponent(
                                        data =
                                            LevelComponentData(
                                                name = stringResource(R.string.info),
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
                            )

                        LogType.WARN ->
                            Content(
                                composable = {
                                    LevelComponent(
                                        data =
                                            LevelComponentData(
                                                name = stringResource(R.string.warn),
                                                color = Color(WARN_COLOR),
                                            ),
                                        modifier =
                                            Modifier
                                                .padding(
                                                    start = 57.dp,
                                                    end = 73.dp,
                                                ),
                                    )
                                },
                            )

                        LogType.CRITICAL ->
                            Content(
                                composable = {
                                    LevelComponent(
                                        data =
                                            LevelComponentData(
                                                name = stringResource(R.string.critical),
                                                color = Color(CRITICAL_COLOR),
                                            ),
                                        modifier =
                                            Modifier
                                                .padding(
                                                    start = 50.dp,
                                                    end = 65.dp,
                                                ),
                                    )
                                },
                            )
                    }
            }
        }
    }
}
