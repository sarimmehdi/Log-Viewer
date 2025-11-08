package com.sarim.sidebar_sessions_domain.usecase

import com.sarim.sidebar_sessions_domain.repository.SidebarSessionsRepository

class GetSelectedSessionUseCase(
    val repository: SidebarSessionsRepository,
) {
    operator fun invoke() = repository.getSelectedSession()
}
