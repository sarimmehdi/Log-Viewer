package com.sarim.sidebar_sessions_di

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.room.Room
import com.sarim.sidebar_dates_data.model.DateDtoSerializer.Companion.DATE_DTO_DATASTORE_QUALIFIER
import com.sarim.sidebar_sessions_data.model.ErrorSessionDtoDao
import com.sarim.sidebar_sessions_data.model.ErrorSessionDtoSerializer
import com.sarim.sidebar_sessions_data.model.InMemorySessionDtoSerializer
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
import com.sarim.utils.test.DefaultDispatchers
import com.sarim.utils.test.Storage
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.module.Module
import org.koin.dsl.lazyModule

fun module() =
    lazyModule {
        singleSessionDataStore()
        singleSessionDao()
        singleSidebarSessionsRepository()
        sidebarSessionsViewModel()
    }

private fun Module.singleSessionDataStore() {
    val sessionDtoDataStoreName = SessionDtoSerializer.DATASTORE_NAME
    single<DataStore<SessionDto>>(named(SESSION_DTO_DATASTORE_QUALIFIER)) {
        DataStoreFactory.create(
            serializer =
                when (BuildConfig.SESSION_DTO_DATASTORE) {
                    Storage.IN_MEMORY.typeName -> InMemorySessionDtoSerializer.create()
                    Storage.ERROR.typeName -> ErrorSessionDtoSerializer.create()
                    else -> SessionDtoSerializer.create(sessionDtoDataStoreName)
                },
            produceFile = { androidContext().dataStoreFile(sessionDtoDataStoreName) },
        )
    }
}

private fun Module.singleSessionDao() {
    single<SessionDtoDao> {
        when (BuildConfig.SESSION_DTO_DATABASE) {
            Storage.IN_MEMORY.typeName ->
                Room
                    .inMemoryDatabaseBuilder(androidContext(), SessionDtoDatabase::class.java)
                    .build()
                    .dao

            Storage.ERROR.typeName ->
                ErrorSessionDtoDao()

            else ->
                Room
                    .databaseBuilder(
                        androidContext(),
                        SessionDtoDatabase::class.java,
                        SessionDtoDatabase.DATABASE_NAME,
                    ).build()
                    .dao
        }
    }
}

private fun Module.singleSidebarSessionsRepository() {
    single<SidebarSessionsRepository> {
        SidebarSessionsRepositoryImpl(
            sessionDtoDataStore = get(named(SESSION_DTO_DATASTORE_QUALIFIER)),
            datesDtoDataStore = get(named(DATE_DTO_DATASTORE_QUALIFIER)),
            dataStoreName = SessionDtoSerializer.DATASTORE_NAME,
            dateDtoDao = get(),
            sessionDtoDao = get(),
        )
    }
}

private fun Module.sidebarSessionsViewModel() {
    viewModel {
        SidebarSessionsScreenViewModel(
            dispatchers = DefaultDispatchers(),
            savedStateHandle = get(),
            useCases =
                SidebarSessionsScreenUseCases(
                    getSessionsUseCase = GetSessionsUseCase(get()),
                    getSelectedSessionUseCase = GetSelectedSessionUseCase(get()),
                    selectSessionUseCase = SelectSessionUseCase(get()),
                    getFilteredSessionsUseCase = GetFilteredSessionsUseCase(get()),
                ),
        )
    }
}
