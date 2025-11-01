package com.sarim.logviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices.PIXEL_TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sarim.logviewer.components.FooterComponent
import com.sarim.logviewer.components.FooterComponentData
import com.sarim.logviewer.components.HeaderComponent
import com.sarim.logviewer.components.Level
import com.sarim.logviewer.components.LevelComponent
import com.sarim.logviewer.components.LevelComponentData
import com.sarim.logviewer.components.Line
import com.sarim.logviewer.components.MainContentComponent
import com.sarim.logviewer.components.MainContentComponentData
import com.sarim.logviewer.components.MainContentListItemComponentData
import com.sarim.logviewer.components.SidebarComponent
import com.sarim.logviewer.components.SidebarComponentData
import com.sarim.logviewer.components.SidebarListItemComponentData
import com.sarim.logviewer.ui.theme.LogViewerTheme
import kotlinx.collections.immutable.toImmutableList

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LogViewerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    AppScreenComponent(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun AppScreenComponent(
    modifier: Modifier = Modifier,
    data: AppScreenComponentData = AppScreenComponentData(),
) {
    Row(
        modifier =
            modifier
                .background(Color(0xFF01070B)),
    ) {
        SidebarComponent(
            data = data.sidebarComponentData,
        )
        Column {
            HeaderComponent(
                modifier =
                    Modifier
                        .padding(
                            top = 24.dp,
                            start = 18.dp,
                            end = 18.dp,
                        ),
            )
            MainContentComponent(
                data = data.mainContentComponentData,
                modifier =
                    Modifier
                        .padding(
                            top = 27.dp,
                            start = 18.dp,
                            end = 18.dp,
                        ),
            )
            FooterComponent(
                data = data.footerComponentData,
                modifier =
                    Modifier
                        .padding(
                            start = 18.dp,
                            end = 18.dp,
                        ),
            )
        }
    }
}

data class AppScreenComponentData(
    val sidebarComponentData: SidebarComponentData = SidebarComponentData(),
    val mainContentComponentData: MainContentComponentData = MainContentComponentData(),
    val footerComponentData: FooterComponentData = FooterComponentData(),
)

@Preview(
    device = PIXEL_TABLET,
)
@Composable
fun AppScreenComponentPreview() {
    val baseLogItems =
        listOf(
            MainContentListItemComponentData(
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                function = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                line = Line.Integer(value = 9999),
                level =
                    Level.Content(
                        composable = {
                            LevelComponent(
                                data =
                                    LevelComponentData(
                                        name = "ERROR",
                                        color = Color(0xFFD30000),
                                    ),
                                modifier =
                                    Modifier
                                        .padding(
                                            start = 57.dp,
                                            end = 73.dp,
                                        ),
                            )
                        },
                    ),
                textSize = 12.sp,
                fontWeight = FontWeight.Bold,
            ),
            MainContentListItemComponentData(
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                function = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                line = Line.Integer(value = 9999),
                level =
                    Level.Content(
                        composable = {
                            LevelComponent(
                                data =
                                    LevelComponentData(
                                        name = "DEBUG",
                                        color = Color(0xFF004AD3),
                                    ),
                                modifier =
                                    Modifier
                                        .padding(
                                            start = 56.dp,
                                            end = 74.dp,
                                        ),
                            )
                        },
                    ),
                textSize = 12.sp,
                fontWeight = FontWeight.Bold,
            ),
            MainContentListItemComponentData(
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                function = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                line = Line.Integer(value = 9999),
                level =
                    Level.Content(
                        composable = {
                            LevelComponent(
                                data =
                                    LevelComponentData(
                                        name = "INFO",
                                        color = Color(0xFF3FD300),
                                    ),
                                modifier =
                                    Modifier
                                        .padding(
                                            start = 57.dp,
                                            end = 73.dp,
                                        ),
                            )
                        },
                    ),
                textSize = 12.sp,
                fontWeight = FontWeight.Bold,
            ),
            MainContentListItemComponentData(
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                function = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                line = Line.Integer(value = 9999),
                level =
                    Level.Content(
                        composable = {
                            LevelComponent(
                                data =
                                    LevelComponentData(
                                        name = "WARN",
                                        color = Color(0xFFD36600),
                                    ),
                                modifier =
                                    Modifier
                                        .padding(
                                            start = 55.dp,
                                            end = 75.dp,
                                        ),
                            )
                        },
                    ),
                textSize = 12.sp,
                fontWeight = FontWeight.Bold,
            ),
            MainContentListItemComponentData(
                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                function = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                line = Line.Integer(value = 9999),
                level =
                    Level.Content(
                        composable = {
                            LevelComponent(
                                data =
                                    LevelComponentData(
                                        name = "CRITICAL",
                                        color = Color(0xFFFF0004),
                                    ),
                                modifier =
                                    Modifier
                                        .padding(
                                            start = 55.dp,
                                            end = 66.dp,
                                        ),
                            )
                        },
                    ),
                textSize = 12.sp,
                fontWeight = FontWeight.Bold,
            ),
        )
    AppScreenComponent(
        data =
            AppScreenComponentData(
                sidebarComponentData =
                    SidebarComponentData(
                        dateObjects =
                            List(10) { index ->
                                SidebarListItemComponentData(
                                    heading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                                    subHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                                    selected = index % 2 == 0,
                                )
                            }.toImmutableList(),
                        sessionObjects =
                            List(10) { index ->
                                SidebarListItemComponentData(
                                    heading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                                    subHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                                    selected = index % 2 == 0,
                                )
                            }.toImmutableList(),
                    ),
                mainContentComponentData =
                    MainContentComponentData(
                        logObjects = List(100) { index -> baseLogItems[index % baseLogItems.size] }.toImmutableList(),
                    ),
                footerComponentData =
                    FooterComponentData(
                        currentPageNumber = 9,
                    ),
            ),
    )
}
