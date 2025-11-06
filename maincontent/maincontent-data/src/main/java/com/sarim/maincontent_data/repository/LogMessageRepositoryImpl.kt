package com.sarim.maincontent_data.repository

import com.sarim.maincontent_domain.model.LogMessage
import com.sarim.maincontent_domain.model.LogType
import com.sarim.maincontent_domain.repository.LogMessageRepository
import com.sarim.utils.ui.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID
import kotlin.random.Random

const val TOTAL_MOCK_ITEMS = 500
const val LOWEST_POSSIBLE_LINE_NUMBER = 0
const val HIGHEST_POSSIBLE_LINE_NUMBER = 9999

class LogMessageRepositoryImpl : LogMessageRepository {
    override fun getLogMessages(pageNumber: Int): Flow<Resource<List<LogMessage>>> =
        flow {
            val mockLogs =
                List(TOTAL_MOCK_ITEMS) { index ->
                    LogMessage(
                        id = UUID.randomUUID().toString(),
                        message = "Log message #${index + 1} on page $pageNumber",
                        className = "ExampleClass${index + 1}",
                        functionName = "exampleFunction${index + 1}",
                        lineNumber =
                            Random.nextInt(
                                LOWEST_POSSIBLE_LINE_NUMBER,
                                HIGHEST_POSSIBLE_LINE_NUMBER,
                            ),
                        logType = LogType.entries.random(),
                    )
                }
            emit(Resource.Success(mockLogs))
        }
}
