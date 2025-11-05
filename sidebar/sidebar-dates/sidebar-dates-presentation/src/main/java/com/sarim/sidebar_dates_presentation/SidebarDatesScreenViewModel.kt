package com.sarim.sidebar_dates_presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarim.sidebar_dates_domain.model.select
import com.sarim.utils.ui.Resource
import com.sarim.utils.ui.snackbarEvent
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

@OptIn(ExperimentalCoroutinesApi::class)
class SidebarDatesScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val useCases: SidebarDatesScreenUseCases,
) : ViewModel() {
    private val _state =
        savedStateHandle.getStateFlow(SIDEBAR_DATES_SCREEN_STATE_KEY, SidebarDatesScreenState())

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
                savedStateHandle[SIDEBAR_DATES_SCREEN_STATE_KEY] = newState
            } else if (datesResource is Resource.Error) {
                snackbarEvent(datesResource.message, R.string.unable_to_get_dates)
            }

            _state.value
        }.launchIn(viewModelScope)
    }

    val state = _state

    fun onEvent(event: SidebarDatesScreenToViewModelEvents) {
        val currState = savedStateHandle[SIDEBAR_DATES_SCREEN_STATE_KEY] as SidebarDatesScreenState? ?: return

        savedStateHandle[SIDEBAR_DATES_SCREEN_STATE_KEY] =
            when (event) {
                is SidebarDatesScreenToViewModelEvents.FilterDates -> currState.copy(datesFilter = event.dateName)
                is SidebarDatesScreenToViewModelEvents.SelectDate -> currState.copy(selectedDate = event.date)
            }
    }

    companion object {
        const val SIDEBAR_DATES_SCREEN_STATE_KEY = "SIDEBAR_DATES_SCREEN_STATE_KEY"
    }
}
