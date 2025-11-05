package com.sarim.sidebar_sessions_di

import com.sarim.sidebar_sessions_data.repository.SidebarSessionsRepositoryImpl
import com.sarim.sidebar_sessions_domain.repository.SidebarSessionsRepository
import com.sarim.sidebar_sessions_domain.usecase.GetSessionsUseCase
import com.sarim.sidebar_sessions_presentation.SidebarSessionsScreenUseCases
import com.sarim.sidebar_sessions_presentation.SidebarSessionsScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.lazyModule

fun module() =
    lazyModule {
        single<SidebarSessionsRepository> {
            SidebarSessionsRepositoryImpl()
        }
        viewModel {
            SidebarSessionsScreenViewModel(
                savedStateHandle = get(),
                useCases =
                    SidebarSessionsScreenUseCases(
                        getSessionsUseCase = GetSessionsUseCase(get()),
                    ),
            )
        }
    }
