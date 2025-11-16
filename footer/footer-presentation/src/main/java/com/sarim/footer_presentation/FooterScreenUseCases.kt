package com.sarim.footer_presentation

import com.sarim.footer_domain.usecase.ChangeCurrentPageNumUseCase
import com.sarim.footer_domain.usecase.GetFooterUseCase
import com.sarim.footer_domain.usecase.GetPageInfoUseCase
import com.sarim.footer_domain.usecase.GetTotalPagesUseCase
import com.sarim.sidebar_sessions_domain.usecase.GetSelectedSessionUseCase

data class FooterScreenUseCases(
    val getFooterUseCase: GetFooterUseCase,
    val changeCurrentPageNumUseCase: ChangeCurrentPageNumUseCase,
    val getTotalPagesUseCase: GetTotalPagesUseCase,
    val getPageInfoUseCase: GetPageInfoUseCase,
    val getSelectedSessionUseCase: GetSelectedSessionUseCase,
)
