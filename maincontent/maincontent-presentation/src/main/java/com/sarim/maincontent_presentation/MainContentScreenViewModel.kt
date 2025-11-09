package com.sarim.maincontent_presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarim.utils.ui.Resource
import com.sarim.utils.ui.snackbarEvent
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
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
            .onStart { loadData() }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(TIMEOUT),
                MainContentScreenState(),
            )

    private fun loadData() {
        viewModelScope.launch {
            useCases.getLogMessagesUseCase().collectLatest { logsResource ->
                val logs =
                    if (logsResource is Resource.Success) {
                        logsResource.data
                    } else {
                        emptyList()
                    }
                if (logsResource is Resource.Error) snackbarEvent(logsResource.message)

                val currState = state.value
                savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY] =
                    currState.copy(
                        logs = logs.toImmutableList(),
                    )
            }
        }
    }

    companion object {
        private const val MAIN_CONTENT_SCREEN_STATE_KEY = "MAIN_CONTENT_SCREEN_STATE_KEY"
        private const val TIMEOUT = 500L
    }
}
