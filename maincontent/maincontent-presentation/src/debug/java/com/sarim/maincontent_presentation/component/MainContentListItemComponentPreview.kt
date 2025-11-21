package com.sarim.maincontent_presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.sp
import com.sarim.maincontent_domain.model.LogMessage.LogType
import com.sarim.maincontent_presentation.component.MainContentListItemComponentData.Level.Content.Companion.fromLogType
import com.sarim.maincontent_presentation.component.MainContentListItemComponentData.Level

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun MainContentListItemComponentPreview(
    @PreviewParameter(MainContentListItemComponentDataParameterProvider::class) data: MainContentListItemComponentData,
) {
    MainContentListItemComponent(
        data = data,
    )
}

internal class MainContentListItemComponentDataParameterProvider : PreviewParameterProvider<MainContentListItemComponentData> {
    override val values =
        sequenceOf(
            MainContentListItemComponentData(
                message = "Message",
                className = "Class",
                functionName = "Function",
                lineNumber = MainContentListItemComponentData.Line.Text(value = "Line"),
                level = Level.Text(value = "Level"),
                textSize = 16.sp,
                fontWeight = FontWeight.Normal,
            ),
            MainContentListItemComponentData(
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                functionName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                lineNumber = MainContentListItemComponentData.Line.Integer(value = 9999),
                level = fromLogType(LogType.ERROR),
                textSize = 12.sp,
                fontWeight = FontWeight.Bold,
            ),
            MainContentListItemComponentData(
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                functionName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                lineNumber = MainContentListItemComponentData.Line.Integer(value = 9999),
                level = fromLogType(LogType.DEBUG),
                textSize = 12.sp,
                fontWeight = FontWeight.Bold,
            ),
            MainContentListItemComponentData(
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                functionName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                lineNumber = MainContentListItemComponentData.Line.Integer(value = 9999),
                level = fromLogType(LogType.INFO),
                textSize = 12.sp,
                fontWeight = FontWeight.Bold,
            ),
            MainContentListItemComponentData(
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                functionName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                lineNumber = MainContentListItemComponentData.Line.Integer(value = 9999),
                level = fromLogType(LogType.WARN),
                textSize = 12.sp,
                fontWeight = FontWeight.Bold,
            ),
            MainContentListItemComponentData(
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                functionName = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                lineNumber = MainContentListItemComponentData.Line.Integer(value = 9999),
                level = fromLogType(LogType.CRITICAL),
                textSize = 12.sp,
                fontWeight = FontWeight.Bold,
            ),
        )
}
