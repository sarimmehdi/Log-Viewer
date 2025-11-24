package com.sarim.footer_di

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.sarim.footer_data.model.FooterDto
import com.sarim.footer_data.model.FooterDtoSerializer
import com.sarim.footer_data.model.FooterDtoSerializer.Companion.FOOTER_DTO_DATASTORE_QUALIFIER
import com.sarim.footer_data.model.InMemoryFooterDtoSerializer
import com.sarim.footer_data.model.ErrorFooterDtoSerializer
import com.sarim.footer_data.repository.FooterRepositoryImpl
import com.sarim.footer_domain.repository.FooterRepository
import com.sarim.utils.test.Storage
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.lazyModule

fun module() =
    lazyModule {
        val footerDtoDataStoreName = FooterDtoSerializer.DATASTORE_NAME
        single<DataStore<FooterDto>>(named(FOOTER_DTO_DATASTORE_QUALIFIER)) {
            DataStoreFactory.create(
                serializer =
                    when (BuildConfig.FOOTER_DTO_DATASTORE) {
                        Storage.IN_MEMORY.typeName -> {
                            InMemoryFooterDtoSerializer.create()
                        }
                        Storage.ERROR.typeName -> {
                            ErrorFooterDtoSerializer.create()
                        }
                        else -> {
                            FooterDtoSerializer.create(footerDtoDataStoreName)
                        }
                    },
                produceFile = { androidContext().dataStoreFile(footerDtoDataStoreName) },
            )
        }

        single<FooterRepository> {
            FooterRepositoryImpl(
                dataStore = get(named(FOOTER_DTO_DATASTORE_QUALIFIER)),
                dataStoreName = footerDtoDataStoreName,
            )
        }
    }
