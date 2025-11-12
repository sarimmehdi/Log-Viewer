package com.sarim.maincontent_presentation

import com.sarim.footer_presentation.FooterScreenUseCases
import com.sarim.maincontent_domain.usecase.GetLogMessagesUseCase

data class MainContentScreenUseCases(
    val getLogMessagesUseCase: GetLogMessagesUseCase,
    val footerScreenUseCases: FooterScreenUseCases,
)
