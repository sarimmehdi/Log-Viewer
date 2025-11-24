package com.sarim.footer_data.model

import androidx.datastore.core.Serializer
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.InputStream
import java.io.OutputStream

class InMemoryFooterDtoSerializer(
    initial: FooterDto = FooterDto.DEFAULT_FOOTER_DTO,
) : Serializer<FooterDto> {
    private val mutex = Mutex()
    private var current: FooterDto = initial

    override val defaultValue: FooterDto
        get() = FooterDto.DEFAULT_FOOTER_DTO

    override suspend fun readFrom(input: InputStream): FooterDto = mutex.withLock { current }

    override suspend fun writeTo(
        t: FooterDto,
        output: OutputStream,
    ) {
        mutex.withLock {
            current =
                t.copy(
                    maxResultsPerPage = t.maxResultsPerPage,
                )
        }
    }

    companion object {
        fun create(): InMemoryFooterDtoSerializer =
            InMemoryFooterDtoSerializer(
                FooterDto(maxResultsPerPage = 5),
            )
    }
}
