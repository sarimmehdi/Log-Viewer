package com.sarim.sidebar_dates_data.repository

import com.sarim.sidebar_dates_data.model.DateDto
import com.sarim.sidebar_dates_data.model.toDate
import com.sarim.sidebar_dates_domain.model.Date
import com.sarim.sidebar_dates_domain.repository.SidebarDatesRepository
import com.sarim.utils.ui.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.UUID

const val TOTAL_MOCK_ITEMS = 10

class SidebarDatesRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : SidebarDatesRepository {
    override val dates: Flow<Resource<List<Date>>> =
        flow {
            val mockDates =
                List(TOTAL_MOCK_ITEMS) { index ->
                    DateDto(
                        id = UUID.randomUUID().toString(),
                        dateHeading = "Date ${index + 1}",
                        dateSessions = 10,
                    )
                }
            emit(Resource.Success(mockDates.map { it.toDate() }))
        }.flowOn(ioDispatcher)
}
