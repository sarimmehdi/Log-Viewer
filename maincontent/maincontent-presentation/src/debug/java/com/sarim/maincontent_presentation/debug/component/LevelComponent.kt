package com.sarim.maincontent_presentation.debug.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sarim.maincontent_presentation.component.ERROR_COLOR
import com.sarim.maincontent_presentation.component.DEBUG_COLOR
import com.sarim.maincontent_presentation.component.INFO_COLOR
import com.sarim.maincontent_presentation.component.WARN_COLOR
import com.sarim.maincontent_presentation.component.CRITICAL_COLOR
import com.sarim.maincontent_presentation.component.LevelComponent
import com.sarim.maincontent_presentation.component.LevelComponentData

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
