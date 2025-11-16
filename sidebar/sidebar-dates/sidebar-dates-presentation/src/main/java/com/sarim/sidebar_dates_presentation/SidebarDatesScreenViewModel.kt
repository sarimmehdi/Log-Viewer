package com.sarim.sidebar_dates_presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarim.sidebar_dates_domain.model.Date
import com.sarim.utils.test.DispatcherProvider
import com.sarim.utils.ui.Resource
import com.sarim.utils.ui.snackbarEvent
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class SidebarDatesScreenViewModel(
    private val dispatchers: DispatcherProvider,
    private val savedStateHandle: SavedStateHandle,
    private val useCases: SidebarDatesScreenUseCases,
) : ViewModel() {
    private val _state =
        savedStateHandle.getStateFlow(SIDEBAR_DATES_SCREEN_STATE_KEY, SidebarDatesScreenState())
    val state =
        _state
            .onStart {
                loadDates()
                getSelectedDate()
                getFilteredDates()
            }.flowOn(dispatchers.main)
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(TIMEOUT),
                SidebarDatesScreenState(),
            )

    private fun loadDates() {
        viewModelScope.launch(dispatchers.main) {
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
    }

    private fun getSelectedDate() {
        viewModelScope.launch(dispatchers.main) {
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

    private fun getFilteredDates() {
        viewModelScope.launch(dispatchers.main) {
            _state
                .map { it.searchString }
                .distinctUntilChanged()
                .collectLatest { searchString ->
                    val currState = state.value
                    if (searchString.isEmpty()) {
                        useCases.getDatesUseCase().collectLatest {
                            when (it) {
                                is Resource.Error<List<Date>> -> snackbarEvent(it.message)
                                is Resource.Success<List<Date>> -> {
                                    savedStateHandle[SIDEBAR_DATES_SCREEN_STATE_KEY] =
                                        currState.copy(dates = it.data.toImmutableList())
                                }
                            }
                        }
                    } else {
                        useCases
                            .getFilteredDatesUseCase(
                                searchString,
                            ).collectLatest { datesResource ->
                                val dates =
                                    if (datesResource is Resource.Success) datesResource.data else emptyList()
                                if (datesResource is Resource.Error) {
                                    snackbarEvent(
                                        datesResource.message,
                                    )
                                }

                                savedStateHandle[SIDEBAR_DATES_SCREEN_STATE_KEY] =
                                    currState.copy(dates = dates.toImmutableList())
                            }
                    }
                }
        }
    }

    fun onEvent(event: SidebarDatesScreenToViewModelEvents) {
        val currState =
            savedStateHandle[SIDEBAR_DATES_SCREEN_STATE_KEY] as SidebarDatesScreenState? ?: return

        when (event) {
            is SidebarDatesScreenToViewModelEvents.FilterDates -> {
                savedStateHandle[SIDEBAR_DATES_SCREEN_STATE_KEY] =
                    currState.copy(searchString = event.dateName)
            }

            is SidebarDatesScreenToViewModelEvents.SelectDate ->
                viewModelScope.launch(dispatchers.main) {
                    useCases.selectDateUseCase(event.date).also {
                        if (it is Resource.Error<Boolean>) {
                            snackbarEvent(it.message)
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
