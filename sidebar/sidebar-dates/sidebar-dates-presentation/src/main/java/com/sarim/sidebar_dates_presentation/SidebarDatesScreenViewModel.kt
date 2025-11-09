package com.sarim.sidebar_dates_presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarim.sidebar_dates_domain.model.Date
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
class SidebarDatesScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val useCases: SidebarDatesScreenUseCases,
) : ViewModel() {
    private val _state =
        savedStateHandle.getStateFlow(SIDEBAR_DATES_SCREEN_STATE_KEY, SidebarDatesScreenState())
    val state =
        _state
            .onStart { loadData() }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(TIMEOUT),
                SidebarDatesScreenState(),
            )

    private fun loadData() {
        viewModelScope.launch {
            useCases.getDatesUseCase().collectLatest { datesResource ->
                val dates =
                    if (datesResource is Resource.Success) datesResource.data.toImmutableList() else emptyList()
                if (datesResource is Resource.Error) snackbarEvent(datesResource.message)

                val currState =
                    savedStateHandle[SIDEBAR_DATES_SCREEN_STATE_KEY] ?: SidebarDatesScreenState()
                savedStateHandle[SIDEBAR_DATES_SCREEN_STATE_KEY] =
                    currState.copy(dates = dates.toImmutableList())
            }
        }

        viewModelScope.launch {
            useCases.getSelectedDateUseCase().collectLatest { selectedResource ->
                val selectedDate =
                    if (selectedResource is Resource.Success) selectedResource.data else null
                if (selectedResource is Resource.Error) snackbarEvent(selectedResource.message)

                val currState =
                    savedStateHandle[SIDEBAR_DATES_SCREEN_STATE_KEY] ?: SidebarDatesScreenState()
                savedStateHandle[SIDEBAR_DATES_SCREEN_STATE_KEY] =
                    currState.copy(selectedDate = selectedDate)
            }
        }
    }

    fun onEvent(event: SidebarDatesScreenToViewModelEvents) {
        val currState =
            savedStateHandle[SIDEBAR_DATES_SCREEN_STATE_KEY] as SidebarDatesScreenState? ?: return

        when (event) {
            is SidebarDatesScreenToViewModelEvents.FilterDates -> {
                viewModelScope.launch {
                    useCases.getFilteredDatesUseCase(event.dateName).collectLatest {
                        when (it) {
                            is Resource.Error<List<Date>> -> snackbarEvent(it.message)
                            is Resource.Success<List<Date>> -> {
                                savedStateHandle[SIDEBAR_DATES_SCREEN_STATE_KEY] =
                                    currState.copy(dates = it.data.toImmutableList())
                            }
                        }
                    }
                }
            }

            is SidebarDatesScreenToViewModelEvents.SelectDate ->
                viewModelScope.launch {
                    useCases.selectDateUseCase(event.date).also {
                        if (it is Resource.Error<Boolean>) {
                            snackbarEvent(it.message)
                        }
                    }
                }

            is SidebarDatesScreenToViewModelEvents.GetAllDates -> {
                viewModelScope.launch {
                    useCases.getDatesUseCase().collectLatest {
                        when (it) {
                            is Resource.Error<List<Date>> -> snackbarEvent(it.message)
                            is Resource.Success<List<Date>> -> {
                                savedStateHandle[SIDEBAR_DATES_SCREEN_STATE_KEY] =
                                    currState.copy(dates = it.data.toImmutableList())
                            }
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val SIDEBAR_DATES_SCREEN_STATE_KEY = "SIDEBAR_DATES_SCREEN_STATE_KEY"
        private const val TIMEOUT = 500L
    }
}
