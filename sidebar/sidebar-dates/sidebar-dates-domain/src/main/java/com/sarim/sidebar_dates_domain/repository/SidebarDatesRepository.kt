package com.sarim.sidebar_dates_domain.repository

import com.sarim.sidebar_dates_domain.model.Date
import com.sarim.utils.ui.Resource
import kotlinx.coroutines.flow.Flow

interface SidebarDatesRepository {
    val dates: Flow<Resource<List<Date>>>

    suspend fun selectDate(date: Date): Resource<Boolean>

    fun getSelectedDate(): Flow<Resource<Date>>

    suspend fun getDatesAccordingToSearchFilter(searchFilter: String): Flow<Resource<List<Date>>>
}
