package com.sarim.sidebar_dates_domain.usecase

import com.sarim.sidebar_dates_domain.model.Date
import com.sarim.sidebar_dates_domain.repository.SidebarDatesRepository

class SelectDateUseCase(
    val repository: SidebarDatesRepository,
) {
    suspend operator fun invoke(date: Date) = repository.selectDate(date)
}
