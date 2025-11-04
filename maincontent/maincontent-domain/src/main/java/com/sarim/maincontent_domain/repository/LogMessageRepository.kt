package com.sarim.maincontent_domain.repository

import com.sarim.maincontent_domain.model.LogMessage
import com.sarim.utils.ui.Resource
import kotlinx.coroutines.flow.Flow

interface LogMessageRepository {
    fun getLogMessages(pageNumber: Int): Flow<Resource<List<LogMessage>>>
}
