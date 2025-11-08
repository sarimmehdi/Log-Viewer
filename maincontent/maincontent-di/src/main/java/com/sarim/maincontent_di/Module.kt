package com.sarim.maincontent_di

import androidx.room.Room
import com.sarim.maincontent_data.model.LogMessageDtoDao
import com.sarim.maincontent_data.model.LogMessageDtoDatabase
import com.sarim.maincontent_data.repository.LogMessageRepositoryImpl
import com.sarim.maincontent_domain.repository.LogMessageRepository
import com.sarim.maincontent_domain.usecase.GetLogMessagesUseCase
import com.sarim.maincontent_presentation.MainContentScreenUseCases
import com.sarim.maincontent_presentation.MainContentScreenViewModel
import com.sarim.sidebar_sessions_domain.usecase.GetSelectedSessionUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.lazyModule

fun module() =
    lazyModule {
        single<LogMessageDtoDao> {
            Room
                .databaseBuilder(
                    androidContext(),
                    LogMessageDtoDatabase::class.java,
                    LogMessageDtoDatabase.DATABASE_NAME,
                ).build()
                .dao
        }

        single<LogMessageRepository> {
            LogMessageRepositoryImpl(
                logMessageDtoDao = get(),
                sessionDtoDao = get(),
            )
        }
        viewModel {
            MainContentScreenViewModel(
                savedStateHandle = get(),
                useCases =
                    MainContentScreenUseCases(
                        getLogMessagesUseCase = GetLogMessagesUseCase(get()),
                        getSelectedSessionUseCase = GetSelectedSessionUseCase(get()),
                    ),
            )
        }
    }
