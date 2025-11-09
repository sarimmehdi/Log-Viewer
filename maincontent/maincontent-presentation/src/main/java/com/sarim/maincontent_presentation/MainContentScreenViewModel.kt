package com.sarim.maincontent_presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarim.utils.ui.Resource
import com.sarim.utils.ui.snackbarEvent
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class MainContentScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val useCases: MainContentScreenUseCases,
) : ViewModel() {
    val state =
        savedStateHandle.getStateFlow(
            MAIN_CONTENT_SCREEN_STATE_KEY,
            MainContentScreenState(),
        )

    init {
        viewModelScope.launch {
            useCases.getSelectedSessionUseCase().collectLatest { selectedResource ->
                val selectedSession =
                    if (selectedResource is Resource.Success) selectedResource.data else null
                if (selectedResource is Resource.Error) snackbarEvent(selectedResource.message)

                val currState =
                    savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY]
                        ?: MainContentScreenState()
                savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY] =
                    currState.copy(selectedSession = selectedSession)
            }
        }

        viewModelScope.launch {
            useCases.getFooterUseCase().collectLatest { selectedResource ->
                val selectedFooter =
                    if (selectedResource is Resource.Success) selectedResource.data else null
                if (selectedResource is Resource.Error) snackbarEvent(selectedResource.message)

                selectedFooter?.let {
                    val currState =
                        savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY]
                            ?: MainContentScreenState()
                    savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY] =
                        currState.copy(currentPageNum = it.currentPageNum)
                }
            }
        }

        viewModelScope.launch {
            combineTransform(
                state.map { it.selectedSession }.filterNotNull().distinctUntilChanged(),
                state.map { it.currentPageNum }.distinctUntilChanged(),
            ) { selectedSession, currentPageNum ->
                emit(
                    useCases.getLogMessagesUseCase(
                        selectedSession.sessionId,
                        currentPageNum,
                    ),
                )
            }.flatMapLatest { it }
                .collectLatest { logsResource ->
                    val logs =
                        if (logsResource is Resource.Success) logsResource.data.toImmutableList() else emptyList()
                    if (logsResource is Resource.Error) snackbarEvent(logsResource.message)

                    val currState = state.value
                    savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY] = currState.copy(logs = logs.toImmutableList())
                }
        }
    }

    companion object {
        const val MAIN_CONTENT_SCREEN_STATE_KEY = "MAIN_CONTENT_SCREEN_STATE_KEY"
    }
}
