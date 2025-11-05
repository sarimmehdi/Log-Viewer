package com.sarim.sidebar_sessions_presentation

import com.sarim.sidebar_sessions_domain.usecase.GetSessionsUseCase

data class SidebarSessionsScreenUseCases(
    val getSessionsUseCase: GetSessionsUseCase,
)
