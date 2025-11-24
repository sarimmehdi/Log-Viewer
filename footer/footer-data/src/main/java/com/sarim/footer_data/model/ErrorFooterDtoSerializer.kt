package com.sarim.footer_data.model

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream

class ErrorFooterDtoSerializer : Serializer<FooterDto> {
    override val defaultValue: FooterDto
        get() = FooterDto.DEFAULT_FOOTER_DTO

    override suspend fun readFrom(input: InputStream): FooterDto =
        throw CorruptionException(
            "Simulated read error for FooterDto",
        )

    override suspend fun writeTo(
        t: FooterDto,
        output: OutputStream,
    ): Unit = throw CorruptionException("Simulated write error for FooterDto")

    companion object {
        fun create(): ErrorFooterDtoSerializer = ErrorFooterDtoSerializer()
    }
}
