package com.sarim.maincontent_presentation

import com.sarim.maincontent_domain.usecase.GetLogMessagesUseCase
import com.sarim.sidebar_sessions_domain.usecase.GetSelectedSessionUseCase

data class MainContentScreenUseCases(
    val getLogMessagesUseCase: GetLogMessagesUseCase,
    val getSelectedSessionUseCase: GetSelectedSessionUseCase,
)
