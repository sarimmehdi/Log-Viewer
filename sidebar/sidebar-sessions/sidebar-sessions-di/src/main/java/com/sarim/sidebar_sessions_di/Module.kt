package com.sarim.sidebar_sessions_di

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.room.Room
import com.sarim.sidebar_dates_data.model.DateDtoSerializer.Companion.DATE_DTO_DATASTORE_QUALIFIER
import com.sarim.sidebar_dates_domain.usecase.GetSelectedDateUseCase
import com.sarim.sidebar_sessions_data.model.SessionDto
import com.sarim.sidebar_sessions_data.model.SessionDtoDao
import com.sarim.sidebar_sessions_data.model.SessionDtoDatabase
import com.sarim.sidebar_sessions_data.model.SessionDtoSerializer
import com.sarim.sidebar_sessions_data.model.SessionDtoSerializer.Companion.SESSION_DTO_DATASTORE_QUALIFIER
import com.sarim.sidebar_sessions_data.repository.SidebarSessionsRepositoryImpl
import com.sarim.sidebar_sessions_domain.repository.SidebarSessionsRepository
import com.sarim.sidebar_sessions_domain.usecase.GetFilteredSessionsUseCase
import com.sarim.sidebar_sessions_domain.usecase.GetSelectedSessionUseCase
import com.sarim.sidebar_sessions_domain.usecase.GetSessionsUseCase
import com.sarim.sidebar_sessions_domain.usecase.SelectSessionUseCase
import com.sarim.sidebar_sessions_presentation.SidebarSessionsScreenUseCases
import com.sarim.sidebar_sessions_presentation.SidebarSessionsScreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.lazyModule

fun module() =
    lazyModule {
        val sessionDtoDataStoreName = SessionDtoSerializer.Companion.DataStoreType.ACTUAL.dataStoreName
        single<DataStore<SessionDto>>(named(SESSION_DTO_DATASTORE_QUALIFIER)) {
            DataStoreFactory.create(
                serializer = SessionDtoSerializer.create(sessionDtoDataStoreName),
                produceFile = { androidContext().dataStoreFile(sessionDtoDataStoreName) },
            )
        }

        single<SessionDtoDao> {
            Room
                .databaseBuilder(
                    androidContext(),
                    SessionDtoDatabase::class.java,
                    SessionDtoDatabase.DATABASE_NAME,
                ).build()
                .dao
        }

        single<SidebarSessionsRepository> {
            SidebarSessionsRepositoryImpl(
                dateDtoDataStore = get(named(DATE_DTO_DATASTORE_QUALIFIER)),
                sessionDtoDataStore = get(named(SESSION_DTO_DATASTORE_QUALIFIER)),
                dataStoreName = sessionDtoDataStoreName,
                dao = get(),
            )
        }
        viewModel {
            SidebarSessionsScreenViewModel(
                savedStateHandle = get(),
                useCases =
                    SidebarSessionsScreenUseCases(
                        getSessionsUseCase = GetSessionsUseCase(get()),
                        getSelectedSessionUseCase = GetSelectedSessionUseCase(get()),
                        getSelectedDateUseCase = GetSelectedDateUseCase(get()),
                        selectSessionUseCase = SelectSessionUseCase(get()),
                        getFilteredSessionsUseCase = GetFilteredSessionsUseCase(get()),
                    ),
            )
        }
    }
