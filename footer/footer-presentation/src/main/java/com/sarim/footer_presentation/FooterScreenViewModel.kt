package com.sarim.footer_presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarim.utils.ui.Resource
import com.sarim.utils.ui.snackbarEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class FooterScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val useCases: FooterScreenUseCases,
) : ViewModel() {
    private val _state = savedStateHandle.getStateFlow(FOOTER_SCREEN_STATE_KEY, FooterScreenState())
    val state =
        _state
            .onStart { loadData() }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(TIMEOUT),
                FooterScreenState(),
            )

    private fun loadData() {
        viewModelScope.launch {
            useCases.getTotalLogMessagesNumUseCase().collectLatest { logCountResource ->
                val logCount =
                    if (logCountResource is Resource.Success) {
                        logCountResource.data
                    } else {
                        0
                    }
                if (logCountResource is Resource.Error) snackbarEvent(logCountResource.message)

                val currState = state.value
                savedStateHandle[FOOTER_SCREEN_STATE_KEY] =
                    currState.copy(
                        totalPages =
                            useCases.getTotalPagesUseCase(
                                logCount,
                                currState.maxResultsPerPage,
                            ),
                        totalLogs = logCount,
                    )
            }
        }

        combine(
            state.map { it.totalPages }.distinctUntilChanged(),
            useCases.getFooterUseCase(),
        ) { totalPages, footerResource ->
            val currState = savedStateHandle[FOOTER_SCREEN_STATE_KEY] ?: FooterScreenState()

            if (footerResource is Resource.Success) {
                val footer = footerResource.data
                savedStateHandle[FOOTER_SCREEN_STATE_KEY] =
                    currState.copy(
                        currentPageNum = footer.currentPageNum,
                        maxResultsPerPage = footer.maxResultsPerPage,
                        numFirstLogOnCurrPage =
                            useCases.getPageInfoUseCase.getStartingLogIndexOnCurrPage(
                                footer.currentPageNum,
                                footer.maxResultsPerPage,
                            ),
                        numLastLogOnCurrPage =
                            useCases.getPageInfoUseCase.getEndingLogIndexOnCurrPage(
                                footer.currentPageNum,
                                footer.maxResultsPerPage,
                            ),
                        canGoToNextPage =
                            useCases.getPageInfoUseCase.canGoToNextPage(
                                footer.currentPageNum,
                                totalPages,
                            ),
                        canGoToPreviousPage =
                            useCases.getPageInfoUseCase.canGoToPreviousPage(
                                footer.currentPageNum,
                            ),
                    )
            } else if (footerResource is Resource.Error) {
                snackbarEvent(footerResource.message)
            }
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: FooterScreenToViewModelEvents) {
        val currState =
            savedStateHandle[FOOTER_SCREEN_STATE_KEY] as FooterScreenState? ?: return

        when (event) {
            is FooterScreenToViewModelEvents.ChangeCurrentPageNumber -> {
                viewModelScope.launch {
                    useCases
                        .changeCurrentPageNumUseCase(event.pageNumber, currState.totalPages)
                        .also {
                            if (it is Resource.Error<Boolean>) {
                                snackbarEvent(it.message)
                            }
                        }
                }
            }

            is FooterScreenToViewModelEvents.ChangeCurrentPageNumberByOne -> {
                viewModelScope.launch {
                    useCases
                        .changeCurrentPageNumUseCase(
                            event.changeType,
                            currState.currentPageNum,
                            currState.totalPages,
                        ).also {
                            if (it is Resource.Error<Boolean>) {
                                snackbarEvent(it.message)
                            }
                        }
                }
            }
        }
    }

    companion object {
        private const val FOOTER_SCREEN_STATE_KEY = "FOOTER_SCREEN_STATE_KEY"
        private const val TIMEOUT = 500L
    }
}
