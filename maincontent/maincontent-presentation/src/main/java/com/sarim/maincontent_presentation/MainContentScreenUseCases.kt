package com.sarim.maincontent_presentation

import com.sarim.footer_presentation.FooterScreenUseCases
import com.sarim.maincontent_domain.usecase.GetFilteredLogsUseCase
import com.sarim.maincontent_domain.usecase.GetLogMessagesUseCase
import com.sarim.maincontent_domain.usecase.GetTotalLogMessagesNumUseCase

data class MainContentScreenUseCases(
    val getLogMessagesUseCase: GetLogMessagesUseCase,
    val getTotalLogMessagesNumUseCase: GetTotalLogMessagesNumUseCase,
    val getFilteredLogsUseCase: GetFilteredLogsUseCase,
    val footerScreenUseCases: FooterScreenUseCases,
)
