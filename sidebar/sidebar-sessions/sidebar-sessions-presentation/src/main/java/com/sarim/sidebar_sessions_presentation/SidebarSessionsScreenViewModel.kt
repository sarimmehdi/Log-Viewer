package com.sarim.sidebar_sessions_presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarim.sidebar_sessions_domain.model.Session
import com.sarim.utils.ui.Resource
import com.sarim.utils.ui.snackbarEvent
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class SidebarSessionsScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val useCases: SidebarSessionsScreenUseCases,
) : ViewModel() {
    val state =
        savedStateHandle.getStateFlow(
            SIDEBAR_SESSIONS_SCREEN_STATE_KEY,
            SidebarSessionsScreenState(),
        )

    init {
        viewModelScope.launch {
            useCases.getSelectedDateUseCase().collectLatest { selectedResource ->
                val selectedDate =
                    if (selectedResource is Resource.Success) selectedResource.data else null
                if (selectedResource is Resource.Error) snackbarEvent(selectedResource.message)

                val currState =
                    savedStateHandle[SIDEBAR_SESSIONS_SCREEN_STATE_KEY]
                        ?: SidebarSessionsScreenState()
                savedStateHandle[SIDEBAR_SESSIONS_SCREEN_STATE_KEY] =
                    currState.copy(selectedDate = selectedDate)
            }
        }

        viewModelScope.launch {
            state
                .map { it.selectedDate }
                .distinctUntilChanged()
                .filterNotNull()
                .flatMapLatest { selectedDate ->
                    useCases.getSessionsUseCase(selectedDate.dateId)
                }.collectLatest { sessionsResource ->
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

    fun onEvent(event: SidebarSessionsScreenToViewModelEvents) {
        val currState =
            savedStateHandle[SIDEBAR_SESSIONS_SCREEN_STATE_KEY] as SidebarSessionsScreenState?
                ?: return

        when (event) {
            is SidebarSessionsScreenToViewModelEvents.FilterSessions -> {
                currState.selectedDate?.let { selectedDate ->
                    viewModelScope.launch {
                        useCases
                            .getFilteredSessionsUseCase(
                                event.sessionName,
                                selectedDate.dateId,
                            ).collectLatest {
                                when (it) {
                                    is Resource.Error<List<Session>> -> snackbarEvent(it.message)
                                    is Resource.Success<List<Session>> -> {
                                        savedStateHandle[SIDEBAR_SESSIONS_SCREEN_STATE_KEY] =
                                            currState.copy(sessions = it.data.toImmutableList())
                                    }
                                }
                            }
                    }
                }
            }

            is SidebarSessionsScreenToViewModelEvents.SelectSession ->
                viewModelScope.launch {
                    useCases.selectSessionUseCase(event.session).also {
                        if (it is Resource.Error<Boolean>) {
                            snackbarEvent(it.message)
                        }
                    }
                }

            is SidebarSessionsScreenToViewModelEvents.GetAllSessionForSelectedDate -> {
                currState.selectedDate?.let { selectedDate ->
                    viewModelScope.launch {
                        useCases.getSessionsUseCase(selectedDate.dateId).collectLatest {
                            when (it) {
                                is Resource.Error<List<Session>> -> snackbarEvent(it.message)
                                is Resource.Success<List<Session>> -> {
                                    savedStateHandle[SIDEBAR_SESSIONS_SCREEN_STATE_KEY] =
                                        currState.copy(sessions = it.data.toImmutableList())
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val SIDEBAR_SESSIONS_SCREEN_STATE_KEY = "SIDEBAR_SESSIONS_SCREEN_STATE_KEY"
    }
}
