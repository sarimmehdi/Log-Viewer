package com.sarim.sidebar_presentation

import com.sarim.sidebar_domain.usecase.GetDatesUseCase
import com.sarim.sidebar_domain.usecase.GetSessionsUseCase

data class SidebarScreenUseCases(
    val getDatesUseCase: GetDatesUseCase,
    val getSessionsUseCase: GetSessionsUseCase,
)