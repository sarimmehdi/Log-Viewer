package com.sarim.sidebar_sessions_data.model

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.IOException
import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class SessionDtoSerializer(
    private val dataStoreName: String,
) : Serializer<SessionDto> {
    override val defaultValue: SessionDto
        get() = SessionDto.NO_SELECTED_SESSION_DTO

    override suspend fun readFrom(input: InputStream): SessionDto {
        try {
            return Json.decodeFromString(
                SessionDto.serializer(),
                input.readBytes().decodeToString(),
            )
        } catch (serialization: SerializationException) {
            throw CorruptionException(
                "Unable to read DateDto from $dataStoreName",
                serialization,
            )
        }
    }

    override suspend fun writeTo(
        t: SessionDto,
        output: OutputStream,
    ) {
        try {
            output.write(
                Json
                    .encodeToString(SessionDto.serializer(), t)
                    .encodeToByteArray(),
            )
        } catch (e: IOException) {
            throw CorruptionException(
                "Unable to write SessionDto to $dataStoreName",
                e,
            )
        }
    }

    companion object {
        const val SESSION_DTO_DATASTORE_QUALIFIER = "SESSION_DTO_DATASTORE_QUALIFIER"
        const val DATASTORE_NAME = "SessionDto.json"

        fun create(dataStoreName: String): SessionDtoSerializer = SessionDtoSerializer(dataStoreName)
    }
}
