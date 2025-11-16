package com.sarim.sidebar_dates_presentation

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sarim.sidebar_dates_domain.model.Date
import com.sarim.utils.test.TestDispatchers
import com.sarim.utils.ui.MessageType
import com.sarim.utils.ui.Resource
import com.sarim.utils.ui.SnackBarController
import com.sarim.utils.ui.snackbarEvent
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class SidebarDatesScreenViewModelTest {
    private val testDispatchers = TestDispatchers()
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var useCases: SidebarDatesScreenUseCases
    private lateinit var viewModel: SidebarDatesScreenViewModel

    private val fakeDates =
        listOf(
            Date(
                dateId = 1L,
                dateHeading = "2025-11-16",
                dateSessions = 0,
            ),
            Date(
                dateId = 2L,
                dateHeading = "2025-11-17",
                dateSessions = 0,
            ),
        )

    @BeforeEach
    fun setup() {
        mockkObject(SnackBarController)
        savedStateHandle = SavedStateHandle()
        useCases = mockk(relaxed = true)
        every { useCases.getDatesUseCase() } returns emptyFlow()
        every { useCases.getSelectedDateUseCase() } returns emptyFlow()
        coEvery { useCases.getFilteredDatesUseCase(any()) } returns emptyFlow()
    }

    @Test
    fun `dates are loaded on initialization`() =
        runTest(testDispatchers.testDispatcher) {
            every { useCases.getDatesUseCase() } returns
                flow { emit(Resource.Success(fakeDates)) }
            viewModel =
                SidebarDatesScreenViewModel(
                    testDispatchers,
                    savedStateHandle,
                    useCases,
                )
            viewModel.state.test {
                val initialState = awaitItem()
                assertThat(initialState.dates).isEmpty()
                val loadedState = awaitItem()
                assertThat(loadedState.dates).isEqualTo(fakeDates)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `dates load error shows snackbar`() =
        runTest(testDispatchers.testDispatcher) {
            val errorMessage = MessageType.StringMessage("")
            every { useCases.getDatesUseCase() } returns flow { emit(Resource.Error(errorMessage)) }
            viewModel =
                SidebarDatesScreenViewModel(
                    testDispatchers,
                    savedStateHandle,
                    useCases,
                )
            viewModel.state.test {
                val errorState = awaitItem()
                assertThat(errorState.dates).isEmpty()
                testScheduler.advanceUntilIdle()
                coVerify { snackbarEvent(errorMessage) }
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `selected date is loaded on initialization`() =
        runTest(testDispatchers.testDispatcher) {
            every { useCases.getSelectedDateUseCase() } returns
                flow { emit(Resource.Success(fakeDates.first())) }
            viewModel =
                SidebarDatesScreenViewModel(
                    testDispatchers,
                    savedStateHandle,
                    useCases,
                )
            viewModel.state.test {
                val initialState = awaitItem()
                assertThat(initialState.selectedDate).isNull()
                val loadedState = awaitItem()
                assertThat(loadedState.selectedDate).isEqualTo(fakeDates.first())
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `selected date error shows snackbar`() =
        runTest(testDispatchers.testDispatcher) {
            val errorMessage = MessageType.StringMessage("")
            every { useCases.getSelectedDateUseCase() } returns
                flow {
                    emit(
                        Resource.Error(
                            errorMessage,
                        ),
                    )
                }
            viewModel =
                SidebarDatesScreenViewModel(
                    testDispatchers,
                    savedStateHandle,
                    useCases,
                )
            viewModel.state.test {
                val errorState = awaitItem()
                assertThat(errorState.selectedDate).isNull()
                testScheduler.advanceUntilIdle()
                coVerify { snackbarEvent(errorMessage) }
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `filtered dates are applied correctly`() =
        runTest(testDispatchers.testDispatcher) {
            val filterString = "2025-11-16"
            coEvery { useCases.getFilteredDatesUseCase(filterString) } returns
                flow { emit(Resource.Success(listOf(fakeDates.first()))) }
            viewModel =
                SidebarDatesScreenViewModel(
                    testDispatchers,
                    savedStateHandle,
                    useCases,
                )
            viewModel.state.test {
                awaitItem()
                viewModel.onEvent(SidebarDatesScreenToViewModelEvents.FilterDates(filterString))
                val filteredState = awaitItem()
                assertThat(filteredState.searchString).isEqualTo(filterString)
                val afterFilteredState = awaitItem()
                assertThat(afterFilteredState.dates).isEqualTo(listOf(fakeDates.first()))
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `filtered dates error shows snackbar`() =
        runTest(testDispatchers.testDispatcher) {
            val errorMessage = MessageType.StringMessage("")
            val filterString = "2025-11-16"
            coEvery { useCases.getFilteredDatesUseCase(filterString) } returns
                flow { emit(Resource.Error(errorMessage)) }

            viewModel = SidebarDatesScreenViewModel(testDispatchers, savedStateHandle, useCases)

            viewModel.state.test {
                awaitItem()
                viewModel.onEvent(SidebarDatesScreenToViewModelEvents.FilterDates(filterString))
                val errorState = awaitItem()
                assertThat(errorState.searchString).isEqualTo(filterString)
                assertThat(errorState.dates).isEmpty()
                testScheduler.advanceUntilIdle()
                coVerify { snackbarEvent(errorMessage) }
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `select date is executed successfully`() =
        runTest(testDispatchers.testDispatcher) {
            val dateToSelect = fakeDates.first()
            coEvery { useCases.selectDateUseCase(dateToSelect) } returns Resource.Success(true)

            viewModel =
                SidebarDatesScreenViewModel(
                    testDispatchers,
                    savedStateHandle,
                    useCases,
                )
            viewModel.onEvent(SidebarDatesScreenToViewModelEvents.SelectDate(dateToSelect))
            testScheduler.advanceUntilIdle()
            coVerify { useCases.selectDateUseCase(dateToSelect) }
        }

    @Test
    fun `select date error shows snackbar`() =
        runTest(testDispatchers.testDispatcher) {
            val dateToSelect = fakeDates.first()
            val errorMessage = MessageType.StringMessage("")
            coEvery { useCases.selectDateUseCase(dateToSelect) } returns
                Resource.Error(errorMessage)

            viewModel =
                SidebarDatesScreenViewModel(
                    testDispatchers,
                    savedStateHandle,
                    useCases,
                )

            viewModel.onEvent(SidebarDatesScreenToViewModelEvents.SelectDate(dateToSelect))

            testScheduler.advanceUntilIdle()
            coVerify { snackbarEvent(errorMessage) }
        }
}
