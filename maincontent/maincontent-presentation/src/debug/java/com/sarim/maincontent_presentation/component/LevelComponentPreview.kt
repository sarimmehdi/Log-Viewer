package com.sarim.maincontent_presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider

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

internal class LevelComponentDataParameterProvider : PreviewParameterProvider<LevelComponentData> {
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
