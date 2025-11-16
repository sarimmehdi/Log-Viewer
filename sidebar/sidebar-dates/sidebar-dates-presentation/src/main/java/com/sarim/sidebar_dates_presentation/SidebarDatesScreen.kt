package com.sarim.sidebar_dates_presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sarim.sidebar_dates_domain.model.Date
import com.sarim.sidebar_dates_presentation.component.SidebarHeaderComponent
import com.sarim.ui.component.CustomScrollableListComponent
import com.sarim.ui.component.CustomScrollableListComponentData
import com.sarim.ui.component.HorizontalDividerComponent
import com.sarim.ui.component.SearchboxComponent
import com.sarim.ui.component.SearchboxComponentData
import com.sarim.ui.component.SidebarListItemComponent
import com.sarim.ui.component.SidebarListItemComponentData
import kotlinx.collections.immutable.ImmutableList

const val SIDEBAR_SCREEN_BACKGROUND_COLOR = 0xFF03111B
const val SIDEBAR_SCREEN_SEARCH_BOX_BACKGROUND_COLOR = 0xFF01070B

@Composable
fun SidebarDatesScreen(
    data: SidebarDatesScreenData,
    onEvent: (SidebarDatesScreenToViewModelEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .width(425.dp)
                .background(Color(SIDEBAR_SCREEN_BACKGROUND_COLOR)),
    ) {
        SidebarHeaderComponent()
        HorizontalDividerComponent()
        SearchboxComponent(
            data =
                SearchboxComponentData(
                    placeholderText = stringResource(R.string.search_dates),
                    iconDescription = stringResource(R.string.search_dates_icon_desc),
                    backgroundColor = Color(SIDEBAR_SCREEN_SEARCH_BOX_BACKGROUND_COLOR),
                    onValueChange = {
                        onEvent(
                            SidebarDatesScreenToViewModelEvents.FilterDates(
                                dateName = it,
                            ),
                        )
                    },
                ),
            modifier =
                Modifier
                    .padding(top = 18.dp, start = 33.dp),
        )
        HorizontalDividerComponent(
            modifier =
                Modifier
                    .padding(top = 20.dp),
        )
        Spacer(
            modifier = Modifier.size(9.dp),
        )
        CustomScrollableListComponent(
            customScrollableListComponentData =
                CustomScrollableListComponentData(
                    contentHeight = 233.dp,
                ) {
                    items(data.dates.size) { index ->
                        val date = data.dates[index]
                        val selectedDate = data.selectedDate
                        SidebarListItemComponent(
                            data =
                                SidebarListItemComponentData(
                                    heading = date.dateHeading,
                                    subHeading =
                                        pluralStringResource(
                                            R.plurals.sessions,
                                            date.dateSessions,
                                            date.dateSessions,
                                        ),
                                    selected = date == selectedDate,
                                ),
                            onClick = {
                                onEvent(
                                    SidebarDatesScreenToViewModelEvents.SelectDate(date),
                                )
                            },
                        )
                    }
                },
        )
        HorizontalDividerComponent(
            modifier =
                Modifier
                    .padding(top = 18.dp),
        )
    }
}

data class SidebarDatesScreenData(
    val dates: ImmutableList<Date>,
    val selectedDate: Date?,
)
