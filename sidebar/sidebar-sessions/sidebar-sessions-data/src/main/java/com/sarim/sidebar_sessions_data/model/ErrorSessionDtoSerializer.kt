package com.sarim.sidebar_sessions_data.model

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream

class ErrorSessionDtoSerializer : Serializer<SessionDto> {
    override val defaultValue: SessionDto
        get() = SessionDto.NO_SELECTED_SESSION_DTO

    override suspend fun readFrom(input: InputStream): SessionDto =
        throw CorruptionException(
            "Simulated read error for SessionDto",
        )

    override suspend fun writeTo(
        t: SessionDto,
        output: OutputStream,
    ): Unit = throw CorruptionException("Simulated write error for SessionDto")

    companion object {
        fun create(): ErrorSessionDtoSerializer = ErrorSessionDtoSerializer()
    }
}
