package com.sarim.sidebar_sessions_presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarim.sidebar_sessions_domain.model.Session
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
class SidebarSessionsScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val useCases: SidebarSessionsScreenUseCases,
) : ViewModel() {
    private val _state =
        savedStateHandle.getStateFlow(
            SIDEBAR_SESSIONS_SCREEN_STATE_KEY,
            SidebarSessionsScreenState(),
        )
    val state =
        _state
            .onStart {
                getSessions()
                getSelectedSession()
                getFilteredSessions()
            }.stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(TIMEOUT),
                SidebarSessionsScreenState(),
            )

    private fun getSessions() {
        viewModelScope.launch {
            useCases.getSessionsUseCase().collectLatest { sessionsResource ->
                val sessions =
                    if (sessionsResource is Resource.Success) {
                        sessionsResource.data.toImmutableList()
                    } else {
                        emptyList()
                    }
                if (sessionsResource is Resource.Error) snackbarEvent(sessionsResource.message)

                val currState = state.value
                savedStateHandle[SIDEBAR_SESSIONS_SCREEN_STATE_KEY] =
                    currState.copy(sessions = sessions.toImmutableList())
            }
        }
    }

    private fun getSelectedSession() {
        viewModelScope.launch {
            useCases.getSelectedSessionUseCase().collectLatest { selectedResource ->
                val selectedSession =
                    if (selectedResource is Resource.Success) selectedResource.data else null
                if (selectedResource is Resource.Error) snackbarEvent(selectedResource.message)

                val currState =
                    savedStateHandle[SIDEBAR_SESSIONS_SCREEN_STATE_KEY]
                        ?: SidebarSessionsScreenState()
                savedStateHandle[SIDEBAR_SESSIONS_SCREEN_STATE_KEY] =
                    currState.copy(selectedSession = selectedSession)
            }
        }
    }

    private fun getFilteredSessions() {
        viewModelScope.launch {
            _state
                .map { it.searchString }
                .distinctUntilChanged()
                .collectLatest { searchString ->
                    val currState = state.value
                    if (searchString.isEmpty()) {
                        useCases.getSessionsUseCase().collectLatest {
                            when (it) {
                                is Resource.Error<List<Session>> -> snackbarEvent(it.message)
                                is Resource.Success<List<Session>> -> {
                                    savedStateHandle[SIDEBAR_SESSIONS_SCREEN_STATE_KEY] =
                                        currState.copy(sessions = it.data.toImmutableList())
                                }
                            }
                        }
                    } else {
                        useCases
                            .getFilteredSessionsUseCase(
                                searchString,
                            ).collectLatest { sessionsResource ->
                                val sessions =
                                    if (sessionsResource is Resource.Success) sessionsResource.data else emptyList()
                                if (sessionsResource is Resource.Error) {
                                    snackbarEvent(
                                        sessionsResource.message,
                                    )
                                }

                                savedStateHandle[SIDEBAR_SESSIONS_SCREEN_STATE_KEY] =
                                    currState.copy(sessions = sessions.toImmutableList())
                            }
                    }
                }
        }
    }

    fun onEvent(event: SidebarSessionsScreenToViewModelEvents) {
        val currState =
            savedStateHandle[SIDEBAR_SESSIONS_SCREEN_STATE_KEY] as SidebarSessionsScreenState?
                ?: return

        when (event) {
            is SidebarSessionsScreenToViewModelEvents.FilterSessions -> {
                savedStateHandle[SIDEBAR_SESSIONS_SCREEN_STATE_KEY] =
                    currState.copy(searchString = event.sessionName)
            }

            is SidebarSessionsScreenToViewModelEvents.SelectSession ->
                viewModelScope.launch {
                    useCases.selectSessionUseCase(event.session).also {
                        if (it is Resource.Error<Boolean>) {
                            snackbarEvent(it.message)
                        }
                    }
                }
        }
    }

    companion object {
        private const val SIDEBAR_SESSIONS_SCREEN_STATE_KEY = "SIDEBAR_SESSIONS_SCREEN_STATE_KEY"
        private const val TIMEOUT = 500L
    }
}
