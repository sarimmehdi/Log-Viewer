package com.sarim.sidebar_dates_domain.repository

import com.sarim.sidebar_dates_domain.model.Date
import com.sarim.utils.ui.Resource
import kotlinx.coroutines.flow.Flow

interface SidebarDatesRepository {
    val dates: Flow<Resource<List<Date>>>
}
