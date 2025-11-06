package com.sarim.sidebar_dates_presentation

import com.sarim.sidebar_dates_domain.usecase.GetDatesUseCase
import com.sarim.sidebar_dates_domain.usecase.GetFilteredDatesUseCase
import com.sarim.sidebar_dates_domain.usecase.GetSelectedDateUseCase
import com.sarim.sidebar_dates_domain.usecase.SelectDateUseCase

data class SidebarDatesScreenUseCases(
    val getDatesUseCase: GetDatesUseCase,
    val getSelectedDateUseCase: GetSelectedDateUseCase,
    val selectDateUseCase: SelectDateUseCase,
    val getFilteredDatesUseCase: GetFilteredDatesUseCase,
)
