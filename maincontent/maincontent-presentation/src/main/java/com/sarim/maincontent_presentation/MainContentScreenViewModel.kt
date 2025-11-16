package com.sarim.maincontent_presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarim.footer_presentation.FooterScreenToViewModelEvents
import com.sarim.header_presentation.HeaderScreenToViewModelEvents
import com.sarim.maincontent_domain.model.LogMessage
import com.sarim.utils.ui.Resource
import com.sarim.utils.ui.snackbarEvent
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class MainContentScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val useCases: MainContentScreenUseCases,
) : ViewModel() {
    private val _state =
        savedStateHandle.getStateFlow(
            MAIN_CONTENT_SCREEN_STATE_KEY,
            MainContentScreenState(),
        )
    val state =
        _state
            .onStart {
                getLogs()
                getTotalLogNumAndPageCount()
                getFooterInfo()
                getSelectedSession()
                resetPageNumOnSessionChange()
                getFilteredLogs()
            }.stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(TIMEOUT),
                MainContentScreenState(),
            )

    private fun getLogs() {
        viewModelScope.launch {
            _state
                .map { it.footerScreenState.currentPageNum }
                .distinctUntilChanged()
                .collectLatest { currentPageNum ->
                    useCases
                        .getLogMessagesUseCase(currentPageNum)
                        .collectLatest { logsResource ->
                            val logs =
                                if (logsResource is Resource.Success) logsResource.data else emptyList()
                            if (logsResource is Resource.Error) snackbarEvent(logsResource.message)

                            val currState = state.value
                            savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY] =
                                currState.copy(logs = logs.toImmutableList())
                        }
                }
        }
    }

    private fun getTotalLogNumAndPageCount() {
        viewModelScope.launch {
            useCases
                .getTotalLogMessagesNumUseCase()
                .collectLatest { logCountResource ->
                    val logCount =
                        if (logCountResource is Resource.Success) {
                            logCountResource.data
                        } else {
                            0
                        }
                    if (logCountResource is Resource.Error) snackbarEvent(logCountResource.message)

                    val currState = state.value
                    savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY] =
                        currState.copy(
                            footerScreenState =
                                currState.footerScreenState.copy(
                                    totalPages =
                                        useCases.footerScreenUseCases.getTotalPagesUseCase(
                                            logCount,
                                            currState.footerScreenState.maxResultsPerPage,
                                        ),
                                    totalLogs = logCount,
                                ),
                        )
                }
        }
    }

    private fun getFooterInfo() {
        viewModelScope.launch {
            _state
                .map { it.footerScreenState.currentPageNum }
                .distinctUntilChanged()
                .collectLatest { totalPages ->
                    useCases
                        .footerScreenUseCases
                        .getFooterUseCase()
                        .collectLatest { footerResource ->
                            val currState =
                                savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY]
                                    ?: MainContentScreenState()
                            if (footerResource is Resource.Success) {
                                val footer = footerResource.data
                                savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY] =
                                    currState.copy(
                                        footerScreenState =
                                            currState.footerScreenState.copy(
                                                currentPageNum = currState.footerScreenState.currentPageNum,
                                                maxResultsPerPage = footer.maxResultsPerPage,
                                                numFirstLogOnCurrPage =
                                                    useCases.footerScreenUseCases.getPageInfoUseCase
                                                        .getStartingLogIndexOnCurrPage(
                                                            currState.footerScreenState.currentPageNum,
                                                            footer.maxResultsPerPage,
                                                        ),
                                                numLastLogOnCurrPage =
                                                    useCases.footerScreenUseCases.getPageInfoUseCase
                                                        .getEndingLogIndexOnCurrPage(
                                                            currState.footerScreenState.currentPageNum,
                                                            footer.maxResultsPerPage,
                                                        ),
                                                canGoToNextPage =
                                                    useCases.footerScreenUseCases.getPageInfoUseCase
                                                        .canGoToNextPage(
                                                            currState.footerScreenState.currentPageNum,
                                                            totalPages,
                                                        ),
                                                canGoToPreviousPage =
                                                    useCases.footerScreenUseCases.getPageInfoUseCase
                                                        .canGoToPreviousPage(
                                                            currState.footerScreenState.currentPageNum,
                                                        ),
                                            ),
                                        headerScreenState =
                                            currState.headerScreenState.copy(
                                                searchString = "",
                                            ),
                                    )
                            } else if (footerResource is Resource.Error) {
                                snackbarEvent(footerResource.message)
                            }
                        }
                }
        }
    }

    private fun getSelectedSession() {
        viewModelScope.launch {
            useCases.footerScreenUseCases
                .getSelectedSessionUseCase()
                .collectLatest { selectedResource ->
                    val selectedSession =
                        if (selectedResource is Resource.Success) selectedResource.data else null
                    if (selectedResource is Resource.Error) snackbarEvent(selectedResource.message)

                    val currState =
                        savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY]
                            ?: MainContentScreenState()
                    savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY] =
                        currState.copy(
                            footerScreenState =
                                currState.footerScreenState.copy(
                                    selectedSession = selectedSession,
                                ),
                        )
                }
        }
    }

    fun resetPageNumOnSessionChange() {
        viewModelScope.launch {
            _state
                .map { it.footerScreenState.selectedSession }
                .distinctUntilChanged()
                .collectLatest { _ ->
                    val currState = state.value
                    savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY] =
                        currState.copy(
                            footerScreenState =
                                currState.footerScreenState.copy(
                                    currentPageNum = 1,
                                ),
                            headerScreenState =
                                currState.headerScreenState.copy(
                                    searchString = "",
                                ),
                        )
                }
        }
    }

    private fun getFilteredLogs() {
        viewModelScope.launch {
            _state
                .map { it.headerScreenState.searchString }
                .distinctUntilChanged()
                .collectLatest { searchString ->
                    val currState = state.value
                    if (searchString.isEmpty()) {
                        useCases
                            .getLogMessagesUseCase(currState.footerScreenState.currentPageNum)
                            .collectLatest {
                                when (it) {
                                    is Resource.Error<List<LogMessage>> -> snackbarEvent(it.message)
                                    is Resource.Success<List<LogMessage>> -> {
                                        savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY] =
                                            currState.copy(logs = it.data.toImmutableList())
                                    }
                                }
                            }
                    } else {
                        useCases
                            .getFilteredLogsUseCase(
                                searchString,
                            ).collectLatest { logsResource ->
                                val logs =
                                    if (logsResource is Resource.Success) logsResource.data else emptyList()
                                if (logsResource is Resource.Error) {
                                    snackbarEvent(
                                        logsResource.message,
                                    )
                                }

                                savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY] =
                                    currState.copy(logs = logs.toImmutableList())
                            }
                    }
                }
        }
    }

    fun onEvent(event: MainContentScreenToViewModelEvents) {
        when (event) {
            is MainContentScreenToViewModelEvents.FooterEvent -> {
                onFooterEvent(event.event)
            }

            is MainContentScreenToViewModelEvents.HeaderEvent -> {
                onHeaderEvent(event.event)
            }
        }
    }

    private fun onFooterEvent(event: FooterScreenToViewModelEvents) {
        val currState =
            savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY] as MainContentScreenState? ?: return
        when (event) {
            is FooterScreenToViewModelEvents.ChangeCurrentPageNumber -> {
                viewModelScope.launch {
                    useCases
                        .footerScreenUseCases
                        .changeCurrentPageNumUseCase(
                            event.pageNumber,
                            currState.footerScreenState.totalPages,
                        ).also {
                            savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY] =
                                currState.copy(
                                    footerScreenState =
                                        currState.footerScreenState.copy(
                                            currentPageNum = it,
                                        ),
                                    headerScreenState =
                                        currState.headerScreenState.copy(
                                            searchString = "",
                                        ),
                                )
                        }
                }
            }

            is FooterScreenToViewModelEvents.ChangeCurrentPageNumberByOne -> {
                viewModelScope.launch {
                    useCases
                        .footerScreenUseCases
                        .changeCurrentPageNumUseCase(
                            event.changeType,
                            currState.footerScreenState.currentPageNum,
                            currState.footerScreenState.totalPages,
                        ).also {
                            savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY] =
                                currState.copy(
                                    footerScreenState =
                                        currState.footerScreenState.copy(
                                            currentPageNum = it,
                                        ),
                                    headerScreenState =
                                        currState.headerScreenState.copy(
                                            searchString = "",
                                        ),
                                )
                        }
                }
            }
        }
    }

    private fun onHeaderEvent(event: HeaderScreenToViewModelEvents) {
        val currState =
            savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY] as MainContentScreenState? ?: return
        when (event) {
            is HeaderScreenToViewModelEvents.FilterLogs ->
                savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY] =
                    currState.copy(
                        headerScreenState =
                            currState.headerScreenState.copy(
                                searchString = event.text,
                            ),
                    )

            is HeaderScreenToViewModelEvents.CangeDropDownType ->
                savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY] =
                    currState.copy(
                        headerScreenState =
                            currState.headerScreenState.copy(
                                dropDownType = event.dropDownType,
                            ),
                    )
        }
    }

    companion object {
        private const val MAIN_CONTENT_SCREEN_STATE_KEY = "MAIN_CONTENT_SCREEN_STATE_KEY"
        private const val TIMEOUT = 500L
    }
}
