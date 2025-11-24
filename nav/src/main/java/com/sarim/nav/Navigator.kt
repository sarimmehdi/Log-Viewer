package com.sarim.nav

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
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.sarim.footer_presentation.FooterScreenData
import com.sarim.header_presentation.HeaderScreenData
import com.sarim.maincontent_presentation.MainContentScreenData
import com.sarim.maincontent_presentation.MainContentScreenToViewModelEvents
import com.sarim.maincontent_presentation.MainContentScreenViewModel
import com.sarim.sidebar_dates_presentation.SidebarDatesScreenData
import com.sarim.sidebar_dates_presentation.SidebarDatesScreenViewModel
import com.sarim.sidebar_sessions_presentation.SidebarSessionsScreenData
import com.sarim.sidebar_sessions_presentation.SidebarSessionsScreenViewModel
import com.sarim.utils.ui.ObserveAsEvents
import com.sarim.utils.ui.SnackBarController
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Navigator(
    sideBarDatesScreenViewModel: SidebarDatesScreenViewModel = koinViewModel(),
    sideBarSessionsScreenViewModel: SidebarSessionsScreenViewModel = koinViewModel(),
    mainContentScreenViewModel: MainContentScreenViewModel = koinViewModel(),
) {
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
                        val sideBarDatesScreenState by
                            sideBarDatesScreenViewModel.state.collectAsStateWithLifecycle()
                        val sideBarSessionsScreenState by
                            sideBarSessionsScreenViewModel.state.collectAsStateWithLifecycle()
                        val mainContentScreenState by
                            mainContentScreenViewModel.state.collectAsStateWithLifecycle()
                        AppScreen(
                            modifier = Modifier.padding(innerPadding),
                            data =
                                AppScreenData(
                                    sidebarDatesScreenData =
                                        SidebarDatesScreenData(
                                            dates = sideBarDatesScreenState.dates.toImmutableList(),
                                            selectedDate = sideBarDatesScreenState.selectedDate,
                                        ),
                                    sidebarSessionsScreenData =
                                        SidebarSessionsScreenData(
                                            sessions = sideBarSessionsScreenState.sessions.toImmutableList(),
                                            selectedSession = sideBarSessionsScreenState.selectedSession,
                                        ),
                                    headerScreenData =
                                        HeaderScreenData(
                                            dropDownType =
                                                mainContentScreenState
                                                    .headerScreenState.dropDownType,
                                            classFilters =
                                                mainContentScreenState
                                                    .headerScreenState.classFilters,
                                            functionFilters =
                                                mainContentScreenState
                                                    .headerScreenState.functionFilters,
                                            logTypeFilters =
                                                mainContentScreenState
                                                    .headerScreenState.logTypeFilters,
                                        ),
                                    mainContentScreenData =
                                        MainContentScreenData(
                                            logs = mainContentScreenState.logs,
                                        ),
                                    footerScreenData =
                                        FooterScreenData(
                                            numberFirstLogOnPage =
                                                mainContentScreenState
                                                    .footerScreenState.numFirstLogOnCurrPage,
                                            numberLastLogOnPage =
                                                mainContentScreenState
                                                    .footerScreenState.numLastLogOnCurrPage,
                                            totalLogs =
                                                mainContentScreenState
                                                    .footerScreenState.totalLogs,
                                            currentPageNumber =
                                                mainContentScreenState
                                                    .footerScreenState.currentPageNum,
                                            totalPages =
                                                mainContentScreenState
                                                    .footerScreenState.totalPages,
                                            canGoToNextPage =
                                                mainContentScreenState
                                                    .footerScreenState.canGoToNextPage,
                                            canGoToPreviousPage =
                                                mainContentScreenState
                                                    .footerScreenState.canGoToPreviousPage,
                                        ),
                                ),
                            onEvent =
                                AppScreenOnEvent(
                                    sidebarDatesScreenToViewModelEvents = sideBarDatesScreenViewModel::onEvent,
                                    sidebarSessionsScreenToViewModelEvents = sideBarSessionsScreenViewModel::onEvent,
                                    headerScreenToViewModelEvents = { event ->
                                        mainContentScreenViewModel.onEvent(
                                            MainContentScreenToViewModelEvents.HeaderEvent(event),
                                        )
                                    },
                                    footerScreenToViewModelEvents = { event ->
                                        mainContentScreenViewModel.onEvent(
                                            MainContentScreenToViewModelEvents.FooterEvent(event),
                                        )
                                    },
                                ),
                        )
                    }
                }
            },
    )
}
