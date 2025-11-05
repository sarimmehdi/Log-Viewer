package com.sarim.sidebar_dates_di

import com.sarim.sidebar_dates_data.repository.SidebarDatesRepositoryImpl
import com.sarim.sidebar_dates_domain.repository.SidebarDatesRepository
import com.sarim.sidebar_dates_domain.usecase.GetDatesUseCase
import com.sarim.sidebar_dates_presentation.SidebarDatesScreenUseCases
import com.sarim.sidebar_dates_presentation.SidebarDatesScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.lazyModule

fun module() =
    lazyModule {
        single<SidebarDatesRepository> {
            SidebarDatesRepositoryImpl()
        }
        viewModel {
            SidebarDatesScreenViewModel(
                savedStateHandle = get(),
                useCases =
                    SidebarDatesScreenUseCases(
                        getDatesUseCase = GetDatesUseCase(get()),
                    ),
            )
        }
    }
