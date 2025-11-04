package com.sarim.maincontent_presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
class MainContentScreenViewModel(
    private val savedStateHandle: SavedStateHandle,
    @Suppress("UNUSED_PARAMETER") private val useCases: MainContentScreenUseCases,
) : ViewModel() {
    private val _state =
        savedStateHandle.getStateFlow(MAIN_CONTENT_SCREEN_STATE_KEY, MainContentScreenState())

    val state = _state

    fun onEvent(event: MainContentScreenToViewModelEvents) {
        val currState = savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY] as MainContentScreenState? ?: return

        savedStateHandle[MAIN_CONTENT_SCREEN_STATE_KEY] =
            when (event) {
                is MainContentScreenToViewModelEvents.AddClassToVisibleClasses ->
                    currState.copy(
                        classNamesToShow = (currState.classNamesToShow + event.className).toImmutableList(),
                    )
                is MainContentScreenToViewModelEvents.AddFunctionToVisibleFunctions ->
                    currState.copy(
                        functionNamesToShow = (currState.functionNamesToShow + event.functionName).toImmutableList(),
                    )
                is MainContentScreenToViewModelEvents.AddLevelToVisibleLevels ->
                    currState.copy(
                        levelsToShow = (currState.levelsToShow + event.level).toImmutableList(),
                    )
                is MainContentScreenToViewModelEvents.FilterMessages ->
                    currState.copy(
                        logMessageFilter = event.messageFilter,
                    )
                is MainContentScreenToViewModelEvents.RemoveClassFromVisibleClasses ->
                    currState.copy(
                        classNamesToShow = (currState.classNamesToShow - event.className).toImmutableList(),
                    )
                is MainContentScreenToViewModelEvents.RemoveFunctionFromVisibleFunctions ->
                    currState.copy(
                        functionNamesToShow = (currState.functionNamesToShow - event.functionName).toImmutableList(),
                    )
                is MainContentScreenToViewModelEvents.RemoveLevelFromVisibleFilters ->
                    currState.copy(
                        levelsToShow = (currState.levelsToShow - event.level).toImmutableList(),
                    )
            }
    }

    companion object {
        const val MAIN_CONTENT_SCREEN_STATE_KEY = "MAIN_CONTENT_SCREEN_STATE_KEY"
    }
}
