package com.sarim.sidebar_sessions_presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class SidebarSessionsScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    @Suppress("UNUSED_PARAMETER") private val useCases: SidebarSessionsScreenUseCases,
) : ViewModel() {
    private val _state =
        savedStateHandle.getStateFlow(SIDEBAR_SESSIONS_SCREEN_STATE_KEY, SidebarSessionsScreenState())

//    init {
//        _state
//            .map { it.dates.getSelected() }
//            .distinctUntilChanged()
//            .flatMapLatest { selectedDate ->
//                useCases
//                    .getSessionsUseCase(selectedDate)
//                    .onStart { emit(Resource.Success(emptyList())) }
//            }.flatMapLatest { sessionsResource ->
//                _state
//                    .map { it.sessionsFilter }
//                    .distinctUntilChanged()
//                    .flatMapLatest { sessionsFilter ->
//                        _state
//                            .map { it.selectedSession }
//                            .distinctUntilChanged()
//                            .map { selectedSession ->
//                                Triple(sessionsResource, sessionsFilter, selectedSession)
//                            }
//                    }
//            }.onEach { (sessionsResource, sessionsFilter, selectedSession) ->
//                when (sessionsResource) {
//                    is Resource.Success -> {
//                        val newState =
//                            _state.value.copy(
//                                sessions =
//                                    useCases.getSessionsUseCase
//                                        .filterSessions(
//                                            sessionsFilter,
//                                            sessionsResource.data,
//                                        ).toImmutableList()
//                                        .select(selectedSession),
//                            )
//                        savedStateHandle[SIDEBAR_SESSIONS_SCREEN_STATE_KEY] = newState
//                    }
//                    is Resource.Error ->
//                        snackbarEvent(
//                            sessionsResource.message,
//                            R.string.unable_to_get_sessions,
//                        )
//                }
//            }.launchIn(viewModelScope)
//    }

    val state = _state

    fun onEvent(event: SidebarSessionsScreenToViewModelEvents) {
        val currState = savedStateHandle[SIDEBAR_SESSIONS_SCREEN_STATE_KEY] as SidebarSessionsScreenState? ?: return

        savedStateHandle[SIDEBAR_SESSIONS_SCREEN_STATE_KEY] =
            when (event) {
                is SidebarSessionsScreenToViewModelEvents.FilterSessions ->
                    currState.copy(sessionsFilter = event.sessionName)
                is SidebarSessionsScreenToViewModelEvents.SelectSession ->
                    currState.copy(selectedSession = event.session)
            }
    }

    companion object {
        const val SIDEBAR_SESSIONS_SCREEN_STATE_KEY = "SIDEBAR_SESSIONS_SCREEN_STATE_KEY"
    }
}
