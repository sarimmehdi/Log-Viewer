package com.sarim.sidebar_sessions_domain.usecase

import com.sarim.sidebar_sessions_domain.model.Session
import com.sarim.sidebar_sessions_domain.repository.SidebarSessionsRepository

class SelectSessionUseCase(
    val repository: SidebarSessionsRepository,
) {
    suspend operator fun invoke(session: Session) = repository.selectSession(session)
}
