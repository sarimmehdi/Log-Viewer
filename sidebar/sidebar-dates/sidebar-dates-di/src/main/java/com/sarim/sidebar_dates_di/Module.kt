package com.sarim.sidebar_dates_di

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import androidx.room.Room
import com.sarim.sidebar_dates_data.model.DateDto
import com.sarim.sidebar_dates_data.model.DateDtoDao
import com.sarim.sidebar_dates_data.model.DateDtoDatabase
import com.sarim.sidebar_dates_data.model.DateDtoSerializer
import com.sarim.sidebar_dates_data.model.DateDtoSerializer.Companion.DATE_DTO_DATASTORE_QUALIFIER
import com.sarim.sidebar_dates_data.repository.SidebarDatesRepositoryImpl
import com.sarim.sidebar_dates_domain.repository.SidebarDatesRepository
import com.sarim.sidebar_dates_domain.usecase.GetDatesUseCase
import com.sarim.sidebar_dates_domain.usecase.GetFilteredDatesUseCase
import com.sarim.sidebar_dates_domain.usecase.GetSelectedDateUseCase
import com.sarim.sidebar_dates_domain.usecase.SelectDateUseCase
import com.sarim.sidebar_dates_presentation.SidebarDatesScreenUseCases
import com.sarim.sidebar_dates_presentation.SidebarDatesScreenViewModel
import com.sarim.utils.test.DefaultDispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.lazyModule

fun module() =
    lazyModule {
        val dateDtoDataStoreName = DateDtoSerializer.Companion.DataStoreType.ACTUAL.dataStoreName
        single<DataStore<DateDto>>(named(DATE_DTO_DATASTORE_QUALIFIER)) {
            DataStoreFactory.create(
                serializer = DateDtoSerializer.create(dateDtoDataStoreName),
                produceFile = { androidContext().dataStoreFile(dateDtoDataStoreName) },
            )
        }

        single<DateDtoDao> {
            Room
                .databaseBuilder(
                    androidContext(),
                    DateDtoDatabase::class.java,
                    DateDtoDatabase.DATABASE_NAME,
                ).build()
                .dao
        }

        single<SidebarDatesRepository> {
            SidebarDatesRepositoryImpl(
                dataStore = get(named(DATE_DTO_DATASTORE_QUALIFIER)),
                dataStoreName = dateDtoDataStoreName,
                dao = get(),
            )
        }
        viewModel {
            SidebarDatesScreenViewModel(
                dispatchers = DefaultDispatchers(),
                savedStateHandle = get(),
                useCases =
                    SidebarDatesScreenUseCases(
                        getDatesUseCase = GetDatesUseCase(get()),
                        getSelectedDateUseCase = GetSelectedDateUseCase(get()),
                        selectDateUseCase = SelectDateUseCase(get()),
                        getFilteredDatesUseCase = GetFilteredDatesUseCase(get()),
                    ),
            )
        }
    }
