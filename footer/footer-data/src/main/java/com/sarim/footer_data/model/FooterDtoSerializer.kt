package com.sarim.footer_data.model

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.IOException
import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class FooterDtoSerializer(
    private val dataStoreName: String,
) : Serializer<FooterDto> {
    override val defaultValue: FooterDto
        get() = FooterDto.DEFAULT_FOOTER_DTO

    override suspend fun readFrom(input: InputStream): FooterDto {
        try {
            return Json.decodeFromString(
                FooterDto.serializer(),
                input.readBytes().decodeToString(),
            )
        } catch (serialization: SerializationException) {
            throw CorruptionException(
                "Unable to read FooterDto from $dataStoreName",
                serialization,
            )
        }
    }

    override suspend fun writeTo(
        t: FooterDto,
        output: OutputStream,
    ) {
        try {
            output.write(
                Json
                    .encodeToString(FooterDto.serializer(), t)
                    .encodeToByteArray(),
            )
        } catch (e: IOException) {
            throw CorruptionException(
                "Unable to write FooterDto to $dataStoreName",
                e,
            )
        }
    }

    companion object {
        const val FOOTER_DTO_DATASTORE_QUALIFIER = "FOOTER_DTO_DATASTORE_QUALIFIER"

        enum class DataStoreType(
            val dataStoreName: String,
        ) {
            ACTUAL("FooterDto.json"),
            TEST("FooterDtoTest.json"),
            TEST_ERROR("FooterDtoTestError.json"),
        }

        fun create(dataStoreName: String): FooterDtoSerializer = FooterDtoSerializer(dataStoreName)
    }
}
