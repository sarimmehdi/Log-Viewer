package com.sarim.sidebar_presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarim.sidebar_domain.model.getSelected
import com.sarim.sidebar_domain.model.select
import com.sarim.utils.ui.Resource
import com.sarim.utils.ui.snackbarEvent
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

@OptIn(ExperimentalCoroutinesApi::class)
class SidebarScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val useCases: SidebarScreenUseCases,
) : ViewModel() {
    private val _state =
        savedStateHandle.getStateFlow(SIDEBAR_SCREEN_STATE_KEY, SidebarScreenState())

    init {
        combine(
            _state.map { it.datesFilter }.distinctUntilChanged(),
            _state.map { it.selectedDate }.distinctUntilChanged(),
            useCases.getDatesUseCase().onStart { emit(Resource.Success(emptyList())) },
        ) { datesFilter, selectedDate, datesResource ->
            if (datesResource is Resource.Success) {
                val newState =
                    _state.value.copy(
                        dates =
                            useCases.getDatesUseCase
                                .filterDates(datesFilter, datesResource.data)
                                .toImmutableList()
                                .select(selectedDate),
                    )
                savedStateHandle[SIDEBAR_SCREEN_STATE_KEY] = newState
            } else if (datesResource is Resource.Error) {
                snackbarEvent(datesResource.message, R.string.unable_to_get_dates)
            }

            _state.value
        }.launchIn(viewModelScope)

        _state
            .map { it.dates.getSelected() }
            .distinctUntilChanged()
            .flatMapLatest { selectedDate ->
                useCases
                    .getSessionsUseCase(selectedDate)
                    .onStart { emit(Resource.Success(emptyList())) }
            }.flatMapLatest { sessionsResource ->
                _state
                    .map { it.sessionsFilter }
                    .distinctUntilChanged()
                    .flatMapLatest { sessionsFilter ->
                        _state
                            .map { it.selectedSession }
                            .distinctUntilChanged()
                            .map { selectedSession ->
                                Triple(sessionsResource, sessionsFilter, selectedSession)
                            }
                    }
            }.onEach { (sessionsResource, sessionsFilter, selectedSession) ->
                when (sessionsResource) {
                    is Resource.Success -> {
                        val newState =
                            _state.value.copy(
                                sessions =
                                    useCases.getSessionsUseCase
                                        .filterSessions(
                                            sessionsFilter,
                                            sessionsResource.data,
                                        ).toImmutableList()
                                        .select(selectedSession),
                            )
                        savedStateHandle[SIDEBAR_SCREEN_STATE_KEY] = newState
                    }
                    is Resource.Error ->
                        snackbarEvent(
                            sessionsResource.message,
                            R.string.unable_to_get_sessions,
                        )
                }
            }.launchIn(viewModelScope)
    }

    val state = _state

    fun onEvent(event: SidebarScreenToViewModelEvents) {
        val currState = savedStateHandle[SIDEBAR_SCREEN_STATE_KEY] as SidebarScreenState? ?: return

        savedStateHandle[SIDEBAR_SCREEN_STATE_KEY] =
            when (event) {
                is SidebarScreenToViewModelEvents.FilterDates -> currState.copy(datesFilter = event.dateName)
                is SidebarScreenToViewModelEvents.FilterSessions -> currState.copy(sessionsFilter = event.sessionName)
                is SidebarScreenToViewModelEvents.SelectDate -> currState.copy(selectedDate = event.date)
                is SidebarScreenToViewModelEvents.SelectSession -> currState.copy(selectedSession = event.session)
            }
    }

    companion object {
        const val SIDEBAR_SCREEN_STATE_KEY = "SIDEBAR_SCREEN_STATE_KEY"
    }
}
