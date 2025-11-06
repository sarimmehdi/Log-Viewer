package com.sarim.sidebar_dates_domain.usecase

import com.sarim.sidebar_dates_domain.repository.SidebarDatesRepository

class GetSelectedDateUseCase(
    val repository: SidebarDatesRepository,
) {
    operator fun invoke() = repository.getSelectedDate()
}
