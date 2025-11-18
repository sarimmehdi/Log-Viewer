package com.sarim.sidebar_dates_presentation.component

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.sidebar_dates_presentation.R
import com.sarim.utils.R as utilsR
import com.sarim.ui.theme.bodyFontFamily

@Composable
internal fun SidebarHeaderComponent(modifier: Modifier = Modifier) {
    Row(
        modifier =
            modifier
                .width(425.dp)
                .height(100.dp),
    ) {
        Text(
            text = stringResource(R.string.dates),
            modifier =
                Modifier
                    .padding(start = 33.dp, top = 20.dp),
            fontWeight = FontWeight.Normal,
            fontSize = 48.sp,
            fontFamily = bodyFontFamily,
            color = Color.White,
        )
        Spacer(
            modifier =
                Modifier
                    .size(173.dp),
        )
        Image(
            painter = painterResource(id = utilsR.drawable.side_bar_close_icon),
            contentDescription = stringResource(R.string.side_bar_close_icon_desc),
            modifier =
                Modifier
                    .padding(top = 36.dp)
                    .size(26.33.dp),
        )
    }
}
