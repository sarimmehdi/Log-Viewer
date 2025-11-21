package com.sarim.header_presentation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.sarim.maincontent_domain.model.LogMessage.LogType
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import com.sarim.header_presentation.HeaderScreenState.DropDownType

private const val HEADER_SCREEN_BACKGROUND_COLOR = 0xFF01070B

@Composable
@Preview(
    device = PIXEL_TABLET,
)
internal fun HeaderScreenPreview(
    @PreviewParameter(HeaderScreenDataParameterProvider::class) data: HeaderScreenData,
) {
    HeaderScreen(
        modifier =
            Modifier
                .background(Color(HEADER_SCREEN_BACKGROUND_COLOR)),
        data = data,
        onEvent = {},
    )
}

private const val TOTAL_CLASSES = 10
private const val TOTAL_FUNCTIONS = 10

internal class HeaderScreenDataParameterProvider : PreviewParameterProvider<HeaderScreenData> {
    override val values =
        sequenceOf(
            HeaderScreenData(
                dropDownType = DropDownType.NONE,
                classFilters = persistentListOf(),
                functionFilters = persistentListOf(),
                logTypeFilters = persistentListOf(),
            ),
            HeaderScreenData(
                dropDownType = DropDownType.CLASS_DROP_DOWN,
                classFilters =
                    List(TOTAL_CLASSES) { i ->
                        HeaderScreenState.ClassFilter(
                            className =
                                "MMMMMMMMMMMMMMMMMMMMMMM".dropLast(i),
                            classFilter = true,
                        )
                    }.toImmutableList(),
                functionFilters =
                    List(TOTAL_FUNCTIONS) { i ->
                        HeaderScreenState.FunctionFilter(
                            functionName = "MMMMMMMMMMMMMMMMMMMMMMM".dropLast(i),
                            functionFilter = true,
                        )
                    }.toImmutableList(),
                logTypeFilters =
                    LogType.entries
                        .map {
                            HeaderScreenState.LogTypeFilter(
                                logType = it,
                                logTypeFilter = true,
                            )
                        }.toImmutableList(),
            ),
            HeaderScreenData(
                dropDownType = DropDownType.FUNCTION_DROP_DOWN,
                classFilters =
                    List(TOTAL_CLASSES) { i ->
                        HeaderScreenState.ClassFilter(
                            className =
                                "MMMMMMMMMMMMMMMMMMMMMMM".dropLast(i),
                            classFilter = true,
                        )
                    }.toImmutableList(),
                functionFilters =
                    List(TOTAL_FUNCTIONS) { i ->
                        HeaderScreenState.FunctionFilter(
                            functionName = "MMMMMMMMMMMMMMMMMMMMMMM".dropLast(i),
                            functionFilter = true,
                        )
                    }.toImmutableList(),
                logTypeFilters =
                    LogType.entries
                        .map {
                            HeaderScreenState.LogTypeFilter(
                                logType = it,
                                logTypeFilter = true,
                            )
                        }.toImmutableList(),
            ),
            HeaderScreenData(
                dropDownType = DropDownType.LOG_TYPE_DROP_DOWN,
                classFilters =
                    List(TOTAL_CLASSES) { i ->
                        HeaderScreenState.ClassFilter(
                            className =
                                "MMMMMMMMMMMMMMMMMMMMMMM".dropLast(i),
                            classFilter = true,
                        )
                    }.toImmutableList(),
                functionFilters =
                    List(TOTAL_FUNCTIONS) { i ->
                        HeaderScreenState.FunctionFilter(
                            functionName = "MMMMMMMMMMMMMMMMMMMMMMM".dropLast(i),
                            functionFilter = true,
                        )
                    }.toImmutableList(),
                logTypeFilters =
                    LogType.entries
                        .map {
                            HeaderScreenState.LogTypeFilter(
                                logType = it,
                                logTypeFilter = true,
                            )
                        }.toImmutableList(),
            ),
        )
}
