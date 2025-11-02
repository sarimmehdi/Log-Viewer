package com.sarim.sidebar_di

import com.sarim.sidebar_data.repository.SidebarRepositoryImpl
import com.sarim.sidebar_domain.repository.SidebarRepository
import com.sarim.sidebar_domain.usecase.GetDatesUseCase
import com.sarim.sidebar_domain.usecase.GetSessionsUseCase
import com.sarim.sidebar_presentation.SidebarScreenUseCases
import com.sarim.sidebar_presentation.SidebarScreenViewModel
import com.sarim.utils.test.DefaultDispatchers
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.lazyModule

fun module(scope: StringQualifier) =
    lazyModule {
        scope(scope) {
            scoped<SidebarRepository> {
                SidebarRepositoryImpl()
            }

            viewModel {
                SidebarScreenViewModel(
                    dispatchers = DefaultDispatchers(),
                    savedStateHandle = get(),
                    useCases =
                        SidebarScreenUseCases(
                            getDatesUseCase = GetDatesUseCase(get()),
                            getSessionsUseCase = GetSessionsUseCase(get()),
                        ),
                )
            }
        }
    }
