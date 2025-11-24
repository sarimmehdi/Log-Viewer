package com.sarim.sidebar_dates_data.model

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.IOException
import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class DateDtoSerializer(
    private val dataStoreName: String,
) : Serializer<DateDto> {
    override val defaultValue: DateDto
        get() = DateDto.NO_SELECTED_DATE_DTO

    override suspend fun readFrom(input: InputStream): DateDto {
        try {
            return Json.decodeFromString(
                DateDto.serializer(),
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
        t: DateDto,
        output: OutputStream,
    ) {
        try {
            output.write(
                Json
                    .encodeToString(DateDto.serializer(), t)
                    .encodeToByteArray(),
            )
        } catch (e: IOException) {
            throw CorruptionException(
                "Unable to write DateDto to $dataStoreName",
                e,
            )
        }
    }

    companion object {
        const val DATE_DTO_DATASTORE_QUALIFIER = "DATE_DTO_DATASTORE_QUALIFIER"
        const val DATASTORE_NAME = "DateDto.json"

        fun create(dataStoreName: String): DateDtoSerializer = DateDtoSerializer(dataStoreName)
    }
}
