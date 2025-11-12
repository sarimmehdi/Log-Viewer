package com.sarim.maincontent_domain.repository

import com.sarim.maincontent_domain.model.LogMessage
import com.sarim.utils.ui.Resource
import kotlinx.coroutines.flow.Flow

interface LogMessageRepository {
    fun getTotalLogMessagesNum(): Flow<Resource<Int>>

    fun getLogMessages(currentPageNum: Int): Flow<Resource<List<LogMessage>>>
}
