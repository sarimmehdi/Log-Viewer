package com.sarim.footer_data.repository

import androidx.datastore.core.DataStore
import com.sarim.footer_data.model.FooterDto
import com.sarim.footer_data.model.FooterDto.Companion.toFooter
import com.sarim.footer_domain.model.Footer
import com.sarim.footer_domain.repository.FooterRepository
import com.sarim.utils.R
import com.sarim.utils.ui.MessageType
import com.sarim.utils.ui.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class FooterRepositoryImpl(
    private val dataStore: DataStore<FooterDto>,
    private val dataStoreName: String,
) : FooterRepository {
    override val footer =
        dataStore.data
            .map {
                Resource.Success(it.toFooter()) as Resource<Footer>
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

    override suspend fun setCurrentPageNumber(currPageNum: Int) =
        try {
            dataStore.updateData {
                it.copy(currentPageNum = currPageNum)
            }
            Resource.Success(true)
        } catch (e: IOException) {
            Resource.Error(
                message =
                    e.localizedMessage?.let {
                        MessageType.StringMessage(it)
                    } ?: MessageType.IntMessage(
                        R.string.unknown_reason_write_exception,
                        dataStoreName,
                        e,
                    ),
            )
        } catch (
            @Suppress("TooGenericExceptionCaught") e: Exception,
        ) {
            Resource.Error(
                message =
                    e.localizedMessage?.let {
                        MessageType.StringMessage(it)
                    } ?: MessageType.IntMessage(R.string.unknown_reason_exception, e),
            )
        }
}
