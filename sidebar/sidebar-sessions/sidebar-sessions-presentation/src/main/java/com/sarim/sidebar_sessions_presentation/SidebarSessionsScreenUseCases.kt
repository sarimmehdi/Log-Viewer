package com.sarim.sidebar_sessions_presentation

import com.sarim.sidebar_sessions_domain.usecase.GetFilteredSessionsUseCase
import com.sarim.sidebar_sessions_domain.usecase.GetSelectedSessionUseCase
import com.sarim.sidebar_sessions_domain.usecase.GetSessionsUseCase
import com.sarim.sidebar_sessions_domain.usecase.SelectSessionUseCase

data class SidebarSessionsScreenUseCases(
    val getSessionsUseCase: GetSessionsUseCase,
    val getSelectedSessionUseCase: GetSelectedSessionUseCase,
    val selectSessionUseCase: SelectSessionUseCase,
    val getFilteredSessionsUseCase: GetFilteredSessionsUseCase,
)
