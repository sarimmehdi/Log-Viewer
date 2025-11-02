package com.sarim.nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.sarim.sidebar_presentation.SidebarScreen
import com.sarim.sidebar_presentation.SidebarScreenData
import com.sarim.sidebar_presentation.SidebarScreenViewModel
import com.sarim.sidebar_presentation.component.SidebarListItemComponentData
import com.sarim.utils.ui.ObserveAsEvents
import com.sarim.utils.ui.SnackBarController
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Navigator(sidebarScreenViewModel: SidebarScreenViewModel = koinViewModel()) {
    val snackbarHostState =
        remember {
            SnackbarHostState()
        }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    ObserveAsEvents(
        flow = SnackBarController.events,
        snackbarHostState,
    ) { event ->
        scope.launch {
            snackbarHostState.currentSnackbarData?.dismiss()

            val result =
                snackbarHostState.showSnackbar(
                    message = event.message.asString(context),
                    actionLabel = event.action?.name?.asString(context),
                    duration = SnackbarDuration.Short,
                )

            if (result == SnackbarResult.ActionPerformed) {
                event.action?.action?.invoke()
            }
        }
    }

    val backStack = rememberNavBackStack(AppScreen)
    NavDisplay(
        entryDecorators =
            listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider =
            entryProvider {
                entry<AppScreen> {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                    ) { innerPadding ->
                        val sidebarScreenState by sidebarScreenViewModel.state.collectAsStateWithLifecycle()
                        AppScreenComponent(
                            modifier = Modifier.padding(innerPadding),
                            data =
                                AppScreenComponentData(
                                    sidebarComponentData =
                                        SidebarScreenData(
                                            dateObjects =
                                                sidebarScreenState.dates
                                                    .map {
                                                        SidebarListItemComponentData(
                                                            heading = it.dateHeading,
                                                            subHeading = context.getString(R.string.num_sessions, it.dateSessions),
                                                        )
                                                    }.toImmutableList(),
                                            sessionObjects =
                                                sidebarScreenState.sessions
                                                    .map {
                                                        SidebarListItemComponentData(
                                                            heading = it.sessionHeading,
                                                            subHeading = context.getString(R.string.num_logs, it.sessionLogs),
                                                        )
                                                    }.toImmutableList(),
                                        ),
                                ),
                        )
                    }
                }
            },
    )
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
        SidebarScreen(
            data = data.sidebarComponentData,
        )
//        Column {
//            HeaderComponent(
//                modifier =
//                    Modifier
//                        .padding(
//                            top = 24.dp,
//                            start = 18.dp,
//                            end = 18.dp,
//                        ),
//            )
//            MainContentComponent(
//                data = data.mainContentComponentData,
//                modifier =
//                    Modifier
//                        .padding(
//                            top = 27.dp,
//                            start = 18.dp,
//                            end = 18.dp,
//                        ),
//            )
//            FooterComponent(
//                data = data.footerComponentData,
//                modifier =
//                    Modifier
//                        .padding(
//                            start = 18.dp,
//                            end = 18.dp,
//                        ),
//            )
//        }
    }
}

data class AppScreenComponentData(
    val sidebarComponentData: SidebarScreenData = SidebarScreenData(),
//    val mainContentComponentData: MainContentComponentData = MainContentComponentData(),
//    val footerComponentData: FooterComponentData = FooterComponentData(),
)

// @Preview(
//    device = PIXEL_TABLET,
// )
// @Composable
// fun AppScreenComponentPreview() {
//    val baseLogItems =
//        listOf(
//            MainContentListItemComponentData(
//                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
//                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
//                function = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
//                line = Line.Integer(value = 9999),
//                level =
//                    Level.Content(
//                        composable = {
//                            LevelComponent(
//                                data =
//                                    LevelComponentData(
//                                        name = "ERROR",
//                                        color = Color(0xFFD30000),
//                                    ),
//                                modifier =
//                                    Modifier
//                                        .padding(
//                                            start = 57.dp,
//                                            end = 73.dp,
//                                        ),
//                            )
//                        },
//                    ),
//                textSize = 12.sp,
//                fontWeight = FontWeight.Bold,
//            ),
//            MainContentListItemComponentData(
//                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
//                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
//                function = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
//                line = Line.Integer(value = 9999),
//                level =
//                    Level.Content(
//                        composable = {
//                            LevelComponent(
//                                data =
//                                    LevelComponentData(
//                                        name = "DEBUG",
//                                        color = Color(0xFF004AD3),
//                                    ),
//                                modifier =
//                                    Modifier
//                                        .padding(
//                                            start = 56.dp,
//                                            end = 74.dp,
//                                        ),
//                            )
//                        },
//                    ),
//                textSize = 12.sp,
//                fontWeight = FontWeight.Bold,
//            ),
//            MainContentListItemComponentData(
//                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
//                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
//                function = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
//                line = Line.Integer(value = 9999),
//                level =
//                    Level.Content(
//                        composable = {
//                            LevelComponent(
//                                data =
//                                    LevelComponentData(
//                                        name = "INFO",
//                                        color = Color(0xFF3FD300),
//                                    ),
//                                modifier =
//                                    Modifier
//                                        .padding(
//                                            start = 57.dp,
//                                            end = 73.dp,
//                                        ),
//                            )
//                        },
//                    ),
//                textSize = 12.sp,
//                fontWeight = FontWeight.Bold,
//            ),
//            MainContentListItemComponentData(
//                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
//                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
//                function = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
//                line = Line.Integer(value = 9999),
//                level =
//                    Level.Content(
//                        composable = {
//                            LevelComponent(
//                                data =
//                                    LevelComponentData(
//                                        name = "WARN",
//                                        color = Color(0xFFD36600),
//                                    ),
//                                modifier =
//                                    Modifier
//                                        .padding(
//                                            start = 55.dp,
//                                            end = 75.dp,
//                                        ),
//                            )
//                        },
//                    ),
//                textSize = 12.sp,
//                fontWeight = FontWeight.Bold,
//            ),
//            MainContentListItemComponentData(
//                message = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
//                className = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
//                function = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
//                line = Line.Integer(value = 9999),
//                level =
//                    Level.Content(
//                        composable = {
//                            LevelComponent(
//                                data =
//                                    LevelComponentData(
//                                        name = "CRITICAL",
//                                        color = Color(0xFFFF0004),
//                                    ),
//                                modifier =
//                                    Modifier
//                                        .padding(
//                                            start = 55.dp,
//                                            end = 66.dp,
//                                        ),
//                            )
//                        },
//                    ),
//                textSize = 12.sp,
//                fontWeight = FontWeight.Bold,
//            ),
//        )
//    AppScreenComponent(
//        data =
//            AppScreenComponentData(
//                sidebarComponentData =
//                    SidebarScreenData(
//                        dateObjects =
//                            List(10) { index ->
//                                SidebarListItemComponentData(
//                                    heading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
//                                    subHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
//                                    selected = index % 2 == 0,
//                                )
//                            }.toImmutableList(),
//                        sessionObjects =
//                            List(10) { index ->
//                                SidebarListItemComponentData(
//                                    heading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
//                                    subHeading = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
//                                    selected = index % 2 == 0,
//                                )
//                            }.toImmutableList(),
//                    ),
//                mainContentComponentData =
//                    MainContentComponentData(
//                        logObjects = List(100) { index -> baseLogItems[index % baseLogItems.size] }.toImmutableList(),
//                    ),
//                footerComponentData =
//                    FooterComponentData(
//                        currentPageNumber = 9,
//                    ),
//            ),
//    )
// }

@Serializable
data object AppScreen : NavKey
