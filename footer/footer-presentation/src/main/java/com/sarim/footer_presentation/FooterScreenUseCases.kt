package com.sarim.footer_presentation

import com.sarim.footer_domain.usecase.ChangeCurrentPageNumUseCase
import com.sarim.footer_domain.usecase.GetFooterUseCase
import com.sarim.footer_domain.usecase.GetPageInfoUseCase
import com.sarim.footer_domain.usecase.GetTotalPagesUseCase
import com.sarim.maincontent_domain.usecase.GetTotalLogMessagesNumUseCase

data class FooterScreenUseCases(
    val getFooterUseCase: GetFooterUseCase,
    val changeCurrentPageNumUseCase: ChangeCurrentPageNumUseCase,
    val getTotalLogMessagesNumUseCase: GetTotalLogMessagesNumUseCase,
    val getTotalPagesUseCase: GetTotalPagesUseCase,
    val getPageInfoUseCase: GetPageInfoUseCase,
)
