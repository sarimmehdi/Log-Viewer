package com.sarim.maincontent_presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.maincontent_domain.model.LogMessage
import com.sarim.maincontent_presentation.component.MainContentListItemComponentData.Level
import com.sarim.maincontent_presentation.component.MainContentListItemComponentData.Level.Content.Companion.fromLogType
import com.sarim.maincontent_presentation.component.MainContentListItemComponentData.Line
import com.sarim.maincontent_presentation.component.MainContentListItemComponent
import com.sarim.maincontent_presentation.component.MainContentListItemComponentData
import com.sarim.ui.component.CustomScrollableListComponent
import com.sarim.ui.component.CustomScrollableListComponentData
import com.sarim.ui.component.HorizontalDividerComponent
import kotlinx.collections.immutable.ImmutableList

const val MAIN_CONTENT_BACKGROUND_COLOR = 0xFF03111B

@Composable
fun MainContentScreen(
    data: MainContentScreenData,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .clip(RoundedCornerShape(10.dp))
                .width(819.dp)
                .height(645.dp)
                .background(Color(MAIN_CONTENT_BACKGROUND_COLOR)),
    ) {
        MainContentListItemComponent(
            data =
                MainContentListItemComponentData(
                    message = stringResource(R.string.message),
                    className = stringResource(R.string.class_name),
                    functionName = stringResource(R.string.function_name),
                    lineNumber = Line.Text(value = stringResource(R.string.line)),
                    level = Level.Text(value = stringResource(R.string.level)),
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
                    items(data.logs.size) { index ->
                        val log = data.logs[index]
                        MainContentListItemComponent(
                            data =
                                MainContentListItemComponentData(
                                    message = log.message,
                                    className = log.className,
                                    functionName = log.functionName,
                                    lineNumber = Line.Integer(log.lineNumber),
                                    level = fromLogType(log.logType),
                                    textSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                ),
                        )
                        HorizontalDividerComponent()
                    }
                },
        )
    }
}

data class MainContentScreenData(
    val logs: ImmutableList<LogMessage>,
)
