package com.sarim.sidebar_sessions_data.model

import androidx.datastore.core.Serializer
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.InputStream
import java.io.OutputStream

class InMemorySessionDtoSerializer(
    initial: SessionDto = SessionDto.NO_SELECTED_SESSION_DTO,
) : Serializer<SessionDto> {
    private val mutex = Mutex()
    private var current: SessionDto = initial

    override val defaultValue: SessionDto
        get() = SessionDto.NO_SELECTED_SESSION_DTO

    override suspend fun readFrom(input: InputStream): SessionDto = mutex.withLock { current }

    override suspend fun writeTo(
        t: SessionDto,
        output: OutputStream,
    ) {
        mutex.withLock {
            current =
                t.copy(
                    dateId = t.dateId,
                    sessionId = t.sessionId,
                    sessionHeading = t.sessionHeading,
                    sessionLogs = t.sessionLogs,
                )
        }
    }

    companion object {
        fun create(): InMemorySessionDtoSerializer =
            InMemorySessionDtoSerializer(
                SessionDto(
                    sessionId = 1L,
                    dateId = 1L,
                    sessionHeading = "In-Memory Session",
                    sessionLogs = 5,
                ),
            )
    }
}
