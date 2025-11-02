package com.sarim.sidebar_presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarim.utils.test.DispatcherProvider
import com.sarim.utils.ui.MessageType
import com.sarim.utils.ui.Resource
import com.sarim.utils.ui.SnackBarController
import com.sarim.utils.ui.SnackbarAction
import com.sarim.utils.ui.SnackbarEvent
import com.sarim.utils.ui.UiText
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import com.sarim.utils.R as utilsR

class SidebarScreenViewModel(
    dispatchers: DispatcherProvider,
    private val savedStateHandle: SavedStateHandle,
    private val useCases: SidebarScreenUseCases,
) : ViewModel() {
    val state = savedStateHandle.getStateFlow(SIDEBAR_SCREEN_STATE_KEY, SidebarScreenState())

    init {
        viewModelScope.launch(dispatchers.main) {
            useCases.getDatesUseCase().collectLatest {
                when (it) {
                    is Resource.Error -> {
                        val message = it.message
                        SnackBarController.sendEvent(
                            event =
                                SnackbarEvent(
                                    message =
                                        when (message) {
                                            is MessageType.IntMessage ->
                                                @Suppress("SpreadOperator")
                                                UiText.StringResource(
                                                    message.message,
                                                    *message.args,
                                                )

                                            is MessageType.StringMessage ->
                                                UiText.StringResource(
                                                    R.string.unable_to_get_dates,
                                                    message.message,
                                                )
                                        },
                                    action =
                                        SnackbarAction(
                                            name = UiText.StringResource(utilsR.string.dismiss),
                                        ),
                                ),
                        )
                    }
                    is Resource.Success -> {
                        val currState = (savedStateHandle[SIDEBAR_SCREEN_STATE_KEY] as SidebarScreenState?)
                        savedStateHandle[SIDEBAR_SCREEN_STATE_KEY] =
                            currState?.copy(
                                dates =
                                    useCases.getDatesUseCase
                                        .filterDates(
                                            date = currState.datesFilter,
                                            dates = it.data,
                                        ).toImmutableList(),
                            )
                    }
                }
            }
        }
        viewModelScope.launch(dispatchers.main) {
            useCases.getSessionsUseCase().collectLatest {
                when (it) {
                    is Resource.Error -> {
                        val message = it.message
                        SnackBarController.sendEvent(
                            event =
                                SnackbarEvent(
                                    message =
                                        when (message) {
                                            is MessageType.IntMessage ->
                                                @Suppress("SpreadOperator")
                                                UiText.StringResource(
                                                    message.message,
                                                    *message.args,
                                                )

                                            is MessageType.StringMessage ->
                                                UiText.StringResource(
                                                    R.string.unable_to_get_sessions,
                                                    message.message,
                                                )
                                        },
                                    action =
                                        SnackbarAction(
                                            name = UiText.StringResource(utilsR.string.dismiss),
                                        ),
                                ),
                        )
                    }
                    is Resource.Success -> {
                        val currState = (savedStateHandle[SIDEBAR_SCREEN_STATE_KEY] as SidebarScreenState?)
                        savedStateHandle[SIDEBAR_SCREEN_STATE_KEY] =
                            currState?.copy(
                                sessions =
                                    useCases.getSessionsUseCase
                                        .filterSessions(
                                            session = currState.sessionsFilter,
                                            sessions = it.data,
                                        ).toImmutableList(),
                            )
                    }
                }
            }
        }
    }

    fun onEvent(event: SidebarScreenToViewModelEvents) {
        when (event) {
            is SidebarScreenToViewModelEvents.FilterDates -> {
                val currState = (savedStateHandle[SIDEBAR_SCREEN_STATE_KEY] as SidebarScreenState?)
                savedStateHandle[SIDEBAR_SCREEN_STATE_KEY] =
                    currState?.copy(
                        datesFilter = event.dateName,
                    )
            }
            is SidebarScreenToViewModelEvents.FilterSessions -> {
                val currState = (savedStateHandle[SIDEBAR_SCREEN_STATE_KEY] as SidebarScreenState?)
                savedStateHandle[SIDEBAR_SCREEN_STATE_KEY] =
                    currState?.copy(
                        datesFilter = event.sessionName,
                    )
            }
        }
    }

    companion object {
        const val SIDEBAR_SCREEN_STATE_KEY = "SIDEBAR_SCREEN_STATE_KEY"
    }
}
