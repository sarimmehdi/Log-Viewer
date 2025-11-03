package com.sarim.sidebar_presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.sidebar_domain.model.Date
import com.sarim.sidebar_domain.model.Session
import com.sarim.sidebar_presentation.R
import com.sarim.sidebar_presentation.SidebarScreenToViewModelEvents
import com.sarim.utils.ui.UiText
import com.sarim.utils.R as utilsR

const val SELECTED_BACKGROUND_COLOR = 0xFFD9D9D9

@Composable
fun SidebarListItemComponent(
    data: SidebarListItemComponentData,
    onEvent: (SidebarScreenToViewModelEvents) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .width(425.dp)
                .height(78.dp)
                .then(
                    if (data.selected) {
                        Modifier.background(Color(SELECTED_BACKGROUND_COLOR).copy(alpha = 0.1f))
                    } else {
                        Modifier
                    },
                ).clickable {
                    onEvent(data.toEvent())
                },
    ) {
        if (data.selected) {
            Box(
                modifier =
                    Modifier
                        .width(20.dp)
                        .height(78.dp)
                        .background(Color.White),
            )
        }
        Column {
            Text(
                text = data.heading,
                modifier =
                    Modifier
                        .padding(
                            start =
                                if (data.selected) {
                                    13.dp
                                } else {
                                    33.dp
                                },
                            top = 5.dp,
                        ),
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
                fontFamily =
                    FontFamily(
                        Font(utilsR.font.inter_24_regular, FontWeight.Normal),
                        Font(utilsR.font.inter_24_medium, FontWeight.Medium),
                        Font(utilsR.font.inter_24_bold, FontWeight.Bold),
                    ),
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(
                modifier =
                    Modifier
                        .size(5.dp),
            )
            Text(
                text = data.subHeading.asString(),
                modifier =
                    Modifier
                        .padding(
                            start =
                                if (data.selected) {
                                    13.dp
                                } else {
                                    33.dp
                                },
                        ),
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                fontFamily =
                    FontFamily(
                        Font(utilsR.font.inter_24_regular, FontWeight.Normal),
                        Font(utilsR.font.inter_24_medium, FontWeight.Medium),
                        Font(utilsR.font.inter_24_bold, FontWeight.Bold),
                    ),
                color = Color.White.copy(alpha = 0.5f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

sealed class SidebarListItemComponentData {
    abstract val heading: String
    abstract val subHeading: UiText
    abstract val selected: Boolean

    abstract fun toEvent(): SidebarScreenToViewModelEvents

    data class DateItem(
        val date: Date,
    ) : SidebarListItemComponentData() {
        override val heading = date.dateHeading
        override val subHeading = UiText.StringResource(R.string.sessions, date.dateSessions)
        override val selected = date.selected

        override fun toEvent() = SidebarScreenToViewModelEvents.SelectDate(date)
    }

    data class SessionItem(
        val session: Session,
    ) : SidebarListItemComponentData() {
        override val heading = session.sessionHeading
        override val subHeading = UiText.StringResource(R.string.logs, session.sessionLogs)
        override val selected = session.selected

        override fun toEvent() = SidebarScreenToViewModelEvents.SelectSession(session)
    }
}
