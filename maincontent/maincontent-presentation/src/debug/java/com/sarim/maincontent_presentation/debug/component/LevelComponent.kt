package com.sarim.maincontent_presentation.debug.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sarim.maincontent_presentation.component.LevelComponent
import com.sarim.maincontent_presentation.component.LevelComponentData

const val ERROR_COLOR = 0xFFD30000
const val DEBUG_COLOR = 0xFF004AD3
const val INFO_COLOR = 0xFF3FD300
const val WARN_COLOR = 0xFFD36600
const val CRITICAL_COLOR = 0xFFFF0004

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun LevelComponentPreview(
    @PreviewParameter(LevelComponentDataParameterProvider::class) data: LevelComponentData,
) {
    LevelComponent(
        data = data,
    )
}

class LevelComponentDataParameterProvider : PreviewParameterProvider<LevelComponentData> {
    override val values =
        sequenceOf(
            LevelComponentData(
                name = "ERROR",
                color = Color(ERROR_COLOR),
            ),
            LevelComponentData(
                name = "DEBUG",
                color = Color(DEBUG_COLOR),
            ),
            LevelComponentData(
                name = "INFO",
                color = Color(INFO_COLOR),
            ),
            LevelComponentData(
                name = "WARN",
                color = Color(WARN_COLOR),
            ),
            LevelComponentData(
                name = "CRITICAL",
                color = Color(CRITICAL_COLOR),
            ),
        )
}
