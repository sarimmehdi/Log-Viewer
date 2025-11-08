package com.sarim.maincontent_data.repository

import com.sarim.maincontent_data.model.LogMessageDto
import com.sarim.maincontent_data.model.LogMessageDto.Companion.toLogMessage
import com.sarim.maincontent_data.model.LogMessageDtoDao
import com.sarim.maincontent_domain.model.LogType
import com.sarim.maincontent_domain.repository.LogMessageRepository
import com.sarim.sidebar_sessions_data.model.SessionDtoDao
import com.sarim.utils.R
import com.sarim.utils.ui.MessageType
import com.sarim.utils.ui.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.collections.map

const val TOTAL_MOCK_ITEMS = 100

class LogMessageRepositoryImpl(
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val logMessageDtoDao: LogMessageDtoDao,
    private val sessionDtoDao: SessionDtoDao,
) : LogMessageRepository {
    init {
        CoroutineScope(ioDispatcher).launch {
            sessionDtoDao.getAll().collectLatest { sessionDtos ->
                sessionDtos.forEach { sessionDto ->
                    val existing = logMessageDtoDao.getAllLogMessagesForSessionId(sessionDto.sessionId).firstOrNull()
                    if (existing.isNullOrEmpty()) {
                        val mockClassNames =
                            listOf(
                                "NetworkManager",
                                "DatabaseHelper",
                                "UserRepository",
                                "MainViewModel",
                                "AuthService",
                                "FileHandler",
                                "ApiClient",
                                "CacheManager",
                                "SettingsViewModel",
                                "NotificationService",
                            )
                        val mockFunctionNames =
                            listOf(
                                "fetchData",
                                "saveUser",
                                "deleteItem",
                                "loadConfig",
                                "syncData",
                                "handleError",
                                "updateCache",
                                "loginUser",
                                "logoutUser",
                                "refreshToken",
                            )
                        val logTypes = LogType.entries.toTypedArray()

                        val initialLogMessages =
                            List(TOTAL_MOCK_ITEMS) { index ->
                                LogMessageDto(
                                    sessionId = sessionDto.sessionId,
                                    message = "${sessionDto.sessionHeading}: Mock log message #$index",
                                    className = mockClassNames[index % mockClassNames.size],
                                    functionName = mockFunctionNames[index % mockFunctionNames.size],
                                    lineNumber = (index + 1) * 10,
                                    logType = logTypes[index % logTypes.size],
                                )
                            }
                        logMessageDtoDao.insertAll(initialLogMessages)
                    }
                }
            }
        }
    }

    override fun getLogMessages(
        sessionId: Long,
        pageNumber: Int,
    ) = logMessageDtoDao
        .getAllLogMessagesForSessionId(sessionId)
        .map { logMessageDto ->
            try {
                Resource.Success(logMessageDto.map { it.toLogMessage() })
            } catch (
                @Suppress("TooGenericExceptionCaught") e: Exception,
            ) {
                Resource.Error(
                    message =
                        e.localizedMessage?.let { MessageType.StringMessage(it) }
                            ?: MessageType.IntMessage(R.string.unknown_reason_exception),
                )
            }
        }.catch { e ->
            emit(
                Resource.Error(
                    message =
                        e.localizedMessage?.let { MessageType.StringMessage(it) }
                            ?: MessageType.IntMessage(R.string.unknown_reason_read_exception),
                ),
            )
        }
}
