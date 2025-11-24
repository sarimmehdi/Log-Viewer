package com.sarim.sidebar_dates_data.model

import androidx.datastore.core.Serializer
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.InputStream
import java.io.OutputStream

class InMemoryDateDtoSerializer(
    initial: DateDto = DateDto.NO_SELECTED_DATE_DTO,
) : Serializer<DateDto> {
    private val mutex = Mutex()
    private var current: DateDto = initial

    override val defaultValue: DateDto
        get() = DateDto.NO_SELECTED_DATE_DTO

    override suspend fun readFrom(input: InputStream): DateDto = mutex.withLock { current }

    override suspend fun writeTo(
        t: DateDto,
        output: OutputStream,
    ) {
        mutex.withLock {
            current =
                t.copy(
                    dateId = t.dateId,
                    dateHeading = t.dateHeading,
                    dateSessions = t.dateSessions,
                )
        }
    }

    companion object {
        fun create(): InMemoryDateDtoSerializer =
            InMemoryDateDtoSerializer(
                DateDto(
                    dateId = 1L,
                    dateHeading = "In-Memory Date",
                    dateSessions = 3,
                ),
            )
    }
}
