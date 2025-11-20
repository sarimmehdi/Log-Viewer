package com.sarim.header_presentation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.header_presentation.HeaderScreenTags.dropDownTestTag
import com.sarim.header_presentation.component.DropDownBoxListItemComponent
import com.sarim.header_presentation.component.DropDownBoxListItemComponentData
import com.sarim.header_presentation.component.DropDownButtonComponent
import com.sarim.header_presentation.component.DropDownButtonComponentData
import com.sarim.ui.component.CustomScrollableListComponent
import com.sarim.ui.component.CustomScrollableListComponentData
import com.sarim.ui.component.SearchboxComponent
import com.sarim.ui.component.SearchboxComponentData
import kotlinx.collections.immutable.ImmutableList

const val SEARCH_BOX_BACKGROUND_COLOR = 0xFF03111B
const val DROP_DOWN_BUTTON_COLOR = 0xFF007AD3

@Composable
fun HeaderScreen(
    modifier: Modifier = Modifier,
    data: HeaderScreenData,
    onEvent: (HeaderScreenToViewModelEvents) -> Unit,
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            modifier =
                Modifier.width(819.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SearchboxComponent(
                data =
                    SearchboxComponentData(
                        placeholderText = stringResource(R.string.search_messages_placeholder),
                        iconDescription = stringResource(R.string.search_messages_icon_desc),
                        backgroundColor = Color(SEARCH_BOX_BACKGROUND_COLOR),
                        onValueChange = {
                            onEvent(
                                HeaderScreenToViewModelEvents.FilterLogs(
                                    text = it,
                                ),
                            )
                        },
                    ),
            )
            Spacer(
                modifier =
                    Modifier
                        .size(19.27.dp),
            )
            DropDownButtonComponent(
                data =
                    DropDownButtonComponentData(
                        name = stringResource(R.string.class_name),
                        borderColor = Color(DROP_DOWN_BUTTON_COLOR),
                        arrowColor = Color(DROP_DOWN_BUTTON_COLOR),
                        textColor = Color(DROP_DOWN_BUTTON_COLOR),
                        fontSize = 24.sp,
                        imageModifier =
                            Modifier
                                .padding(top = 28.dp, end = 16.6.dp)
                                .size(
                                    width = 18.4.dp,
                                    height = 8.dp,
                                ),
                        textModifier =
                            Modifier
                                .padding(
                                    top = 17.dp,
                                    start = 10.dp,
                                ),
                    ),
                modifier =
                    Modifier
                        .width(138.75.dp)
                        .height(57.dp),
            )
            Spacer(
                modifier =
                    Modifier
                        .size(21.2.dp),
            )
            DropDownButtonComponent(
                data =
                    DropDownButtonComponentData(
                        name = stringResource(R.string.function_name),
                        borderColor = Color(DROP_DOWN_BUTTON_COLOR),
                        arrowColor = Color(DROP_DOWN_BUTTON_COLOR),
                        textColor = Color(DROP_DOWN_BUTTON_COLOR),
                        fontSize = 24.sp,
                        imageModifier =
                            Modifier
                                .padding(top = 28.dp, end = 16.6.dp)
                                .size(
                                    width = 18.4.dp,
                                    height = 8.dp,
                                ),
                        textModifier =
                            Modifier
                                .padding(
                                    top = 17.dp,
                                    start = 10.dp,
                                ),
                    ),
                modifier =
                    Modifier
                        .width(154.16.dp)
                        .height(57.dp),
            )
            Spacer(
                modifier =
                    Modifier
                        .size(22.16.dp),
            )
            DropDownButtonComponent(
                data =
                    DropDownButtonComponentData(
                        name = stringResource(R.string.level_name),
                        borderColor = Color(DROP_DOWN_BUTTON_COLOR),
                        arrowColor = Color(DROP_DOWN_BUTTON_COLOR),
                        textColor = Color(DROP_DOWN_BUTTON_COLOR),
                        fontSize = 24.sp,
                        imageModifier =
                            Modifier
                                .padding(top = 28.dp, end = 16.6.dp)
                                .size(
                                    width = 18.4.dp,
                                    height = 8.dp,
                                ),
                        textModifier =
                            Modifier
                                .padding(
                                    top = 17.dp,
                                    start = 10.dp,
                                ),
                    ),
                modifier =
                    Modifier
                        .width(138.75.dp)
                        .height(57.dp),
            )
        }
        if (data.dropDownType != DropDownType.NONE) {
            CustomScrollableListComponent(
                customScrollableListComponentData =
                    CustomScrollableListComponentData(
                        contentHeight = 159.dp,
                    ) {
                        when (data.dropDownType) {
                            DropDownType.CLASS_DROP_DOWN -> {
                                items(data.classFilters.size) { index ->
                                    val classFilter = data.classFilters[index]
                                    DropDownBoxListItemComponent(
                                        data =
                                            DropDownBoxListItemComponentData(
                                                itemName = classFilter.className,
                                                itemFilterOn = classFilter.classFilter,
                                            ),
                                    )
                                }
                            }

                            DropDownType.FUNCTION_DROP_DOWN -> {
                                items(data.functionFilters.size) { index ->
                                    val functionFilter = data.functionFilters[index]
                                    DropDownBoxListItemComponent(
                                        data =
                                            DropDownBoxListItemComponentData(
                                                itemName = functionFilter.functionName,
                                                itemFilterOn = functionFilter.functionFilter,
                                            ),
                                    )
                                }
                            }

                            DropDownType.LOG_TYPE_DROP_DOWN -> {
                                items(data.logTypeFilters.size) { index ->
                                    val logTypeFilter = data.logTypeFilters[index]
                                    DropDownBoxListItemComponent(
                                        data =
                                            DropDownBoxListItemComponentData(
                                                itemName = logTypeFilter.logType.logTypeName,
                                                itemFilterOn = logTypeFilter.logTypeFilter,
                                            ),
                                    )
                                }
                            }

                            DropDownType.NONE -> {}
                        }
                    },
                modifier =
                    Modifier
                        .then(
                            when (data.dropDownType) {
                                DropDownType.FUNCTION_DROP_DOWN -> {
                                    Modifier.padding(
                                        start = 176.55.dp,
                                    )
                                }

                                DropDownType.LOG_TYPE_DROP_DOWN -> {
                                    Modifier.padding(
                                        start = 337.46.dp,
                                    )
                                }

                                else -> Modifier
                            },
                        ).border(
                            1.dp,
                            Color(DROP_DOWN_BUTTON_COLOR),
                            RoundedCornerShape(13.5.dp),
                        ).widthIn(max = 500.dp)
                        .testTag(dropDownTestTag(data.dropDownType)),
            )
        }
    }
}

data class HeaderScreenData(
    val dropDownType: DropDownType,
    val classFilters: ImmutableList<ClassFilter>,
    val functionFilters: ImmutableList<FunctionFilter>,
    val logTypeFilters: ImmutableList<LogTypeFilter>,
)

object HeaderScreenTags {
    const val P = "HEADER_SCREEN"
    const val S = "_TEST_TAG"

    fun dropDownTestTag(dropDownType: DropDownType) = "${P}DROP_DOWN_${dropDownType.name}$S"
}
