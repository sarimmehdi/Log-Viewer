package com.sarim.sidebar_dates_data.model

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import java.io.InputStream
import java.io.OutputStream

class ErrorDateDtoSerializer : Serializer<DateDto> {
    override val defaultValue: DateDto
        get() = DateDto.NO_SELECTED_DATE_DTO

    override suspend fun readFrom(input: InputStream): DateDto =
        throw CorruptionException(
            "Simulated read error for DateDto",
        )

    override suspend fun writeTo(
        t: DateDto,
        output: OutputStream,
    ): Unit = throw CorruptionException("Simulated write error for DateDto")

    companion object {
        fun create(): ErrorDateDtoSerializer = ErrorDateDtoSerializer()
    }
}
