package com.sarim.footer_di

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.sarim.footer_data.model.FooterDto
import com.sarim.footer_data.model.FooterDtoSerializer
import com.sarim.footer_data.model.FooterDtoSerializer.Companion.FOOTER_DTO_DATASTORE_QUALIFIER
import com.sarim.footer_data.repository.FooterRepositoryImpl
import com.sarim.footer_domain.repository.FooterRepository
import com.sarim.footer_domain.usecase.ChangeCurrentPageNumUseCase
import com.sarim.footer_domain.usecase.GetFooterUseCase
import com.sarim.footer_domain.usecase.GetPageInfoUseCase
import com.sarim.footer_domain.usecase.GetTotalPagesUseCase
import com.sarim.footer_presentation.FooterScreenUseCases
import com.sarim.footer_presentation.FooterScreenViewModel
import com.sarim.maincontent_domain.usecase.GetTotalLogMessagesNumUseCase
import com.sarim.sidebar_sessions_domain.usecase.GetSelectedSessionUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.lazyModule

fun module() =
    lazyModule {
        val footerDtoDataStoreName = FooterDtoSerializer.Companion.DataStoreType.ACTUAL.dataStoreName
        single<DataStore<FooterDto>>(named(FOOTER_DTO_DATASTORE_QUALIFIER)) {
            DataStoreFactory.create(
                serializer = FooterDtoSerializer.create(footerDtoDataStoreName),
                produceFile = { androidContext().dataStoreFile(footerDtoDataStoreName) },
            )
        }

        single<FooterRepository> {
            FooterRepositoryImpl(
                dataStore = get(named(FOOTER_DTO_DATASTORE_QUALIFIER)),
                dataStoreName = footerDtoDataStoreName,
            )
        }
        viewModel {
            FooterScreenViewModel(
                savedStateHandle = get(),
                useCases =
                    FooterScreenUseCases(
                        getFooterUseCase = GetFooterUseCase(get()),
                        changeCurrentPageNumUseCase = ChangeCurrentPageNumUseCase(get()),
                        getSelectedSessionUseCase = GetSelectedSessionUseCase(get()),
                        getTotalLogMessagesNumUseCase = GetTotalLogMessagesNumUseCase(get()),
                        getTotalPagesUseCase = GetTotalPagesUseCase(),
                        getPageInfoUseCase = GetPageInfoUseCase(),
                    ),
            )
        }
    }
