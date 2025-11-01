package com.sarim.logviewer.components

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Composable
fun DropDownBoxComponent(
    modifier: Modifier = Modifier,
    data: DropDownBoxComponentData = DropDownBoxComponentData(),
) {
    CustomScrollableListComponent(
        customScrollableListComponentData =
            CustomScrollableListComponentData(
                contentHeight = 159.dp,
            ) {
                items(data.dropDownBoxListItems.size) { index ->
                    DropDownBoxListItemComponent(
                        data = data.dropDownBoxListItems[index],
                    )
                }
            },
        modifier =
            modifier
                .width(163.dp),
    )
}

data class DropDownBoxComponentData(
    val dropDownBoxListItems: ImmutableList<DropDownBoxListItemComponentData> = persistentListOf(),
)

@Preview(
    device = PIXEL_TABLET,
)
@Composable
fun DropDownBoxComponentPreview() {
    DropDownBoxComponent(
        data =
            DropDownBoxComponentData(
                dropDownBoxListItems =
                    List(10) { index ->
                        DropDownBoxListItemComponentData(
                            itemName = "Class 1",
                            itemFilterOn = index % 2 == 0,
                        )
                    }.toImmutableList(),
            ),
    )
}
