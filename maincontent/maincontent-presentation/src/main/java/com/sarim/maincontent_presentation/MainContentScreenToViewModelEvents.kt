package com.sarim.maincontent_presentation

import com.sarim.maincontent_domain.model.LogType

sealed interface MainContentScreenToViewModelEvents {
    data class FilterMessages(
        val messageFilter: String,
    ) : MainContentScreenToViewModelEvents

    data class AddClassToVisibleClasses(
        val className: String,
    ) : MainContentScreenToViewModelEvents

    data class AddFunctionToVisibleFunctions(
        val functionName: String,
    ) : MainContentScreenToViewModelEvents

    data class AddLevelToVisibleLevels(
        val level: LogType,
    ) : MainContentScreenToViewModelEvents

    data class RemoveClassFromVisibleClasses(
        val className: String,
    ) : MainContentScreenToViewModelEvents

    data class RemoveFunctionFromVisibleFunctions(
        val functionName: String,
    ) : MainContentScreenToViewModelEvents

    data class RemoveLevelFromVisibleFilters(
        val level: LogType,
    ) : MainContentScreenToViewModelEvents
}
