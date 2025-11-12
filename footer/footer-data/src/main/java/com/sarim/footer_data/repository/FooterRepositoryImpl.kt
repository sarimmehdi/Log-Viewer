package com.sarim.footer_data.repository

import androidx.datastore.core.DataStore
import com.sarim.footer_data.model.FooterDto
import com.sarim.footer_data.model.createFooter
import com.sarim.footer_domain.model.Footer
import com.sarim.footer_domain.repository.FooterRepository
import com.sarim.utils.R
import com.sarim.utils.ui.MessageType
import com.sarim.utils.ui.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class FooterRepositoryImpl(
    dataStore: DataStore<FooterDto>,
    private val dataStoreName: String,
) : FooterRepository {
    override val footer =
        dataStore.data
            .map {
                Resource.Success(createFooter(it)) as Resource<Footer>
            }.catch { e ->
                emit(
                    Resource.Error(
                        message =
                            e.localizedMessage?.let {
                                MessageType.StringMessage(it)
                            } ?: MessageType.IntMessage(
                                R.string.unknown_reason_read_exception,
                                dataStoreName,
                                e,
                            ),
                    ),
                )
            }
}
