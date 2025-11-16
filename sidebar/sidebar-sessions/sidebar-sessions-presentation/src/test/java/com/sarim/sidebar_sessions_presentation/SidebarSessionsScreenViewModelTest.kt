package com.sarim.sidebar_sessions_presentation

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sarim.sidebar_sessions_domain.model.Session
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
internal class SidebarSessionsScreenViewModelTest {
    private val testDispatchers = TestDispatchers()
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var useCases: SidebarSessionsScreenUseCases
    private lateinit var viewModel: SidebarSessionsScreenViewModel

    private val fakeSessions =
        listOf(
            Session(
                sessionId = 1L,
                dateId = 1L,
                sessionHeading = "Morning Session",
                sessionLogs = 3,
            ),
            Session(
                sessionId = 2L,
                dateId = 1L,
                sessionHeading = "Evening Session",
                sessionLogs = 5,
            ),
        )

    @BeforeEach
    fun setup() {
        mockkObject(SnackBarController)
        savedStateHandle = SavedStateHandle()
        useCases = mockk(relaxed = true)

        every { useCases.getSessionsUseCase() } returns emptyFlow()
        every { useCases.getSelectedSessionUseCase() } returns emptyFlow()
        coEvery { useCases.getFilteredSessionsUseCase(any()) } returns emptyFlow()
    }

    @Test
    fun `sessions are loaded on initialization`() =
        runTest(testDispatchers.testDispatcher) {
            every { useCases.getSessionsUseCase() } returns flow { emit(Resource.Success(fakeSessions)) }
            viewModel = SidebarSessionsScreenViewModel(savedStateHandle, useCases)

            viewModel.state.test {
                val initialState = awaitItem()
                assertThat(initialState.sessions).isEmpty()
                val loadedState = awaitItem()
                assertThat(loadedState.sessions).isEqualTo(fakeSessions)
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `sessions load error shows snackbar`() =
        runTest(testDispatchers.testDispatcher) {
            val errorMessage = MessageType.StringMessage("")
            every { useCases.getSessionsUseCase() } returns flow { emit(Resource.Error(errorMessage)) }

            viewModel = SidebarSessionsScreenViewModel(savedStateHandle, useCases)

            viewModel.state.test {
                val errorState = awaitItem()
                assertThat(errorState.sessions).isEmpty()
                testScheduler.advanceUntilIdle()
                coVerify { snackbarEvent(errorMessage) }
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `selected session is loaded on initialization`() =
        runTest(testDispatchers.testDispatcher) {
            every { useCases.getSelectedSessionUseCase() } returns
                flow {
                    emit(
                        Resource.Success(
                            fakeSessions.first(),
                        ),
                    )
                }
            viewModel = SidebarSessionsScreenViewModel(savedStateHandle, useCases)

            viewModel.state.test {
                val initialState = awaitItem()
                assertThat(initialState.selectedSession).isNull()
                val loadedState = awaitItem()
                assertThat(loadedState.selectedSession).isEqualTo(fakeSessions.first())
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `selected session error shows snackbar`() =
        runTest(testDispatchers.testDispatcher) {
            val errorMessage = MessageType.StringMessage("")
            every { useCases.getSelectedSessionUseCase() } returns
                flow {
                    emit(
                        Resource.Error(
                            errorMessage,
                        ),
                    )
                }

            viewModel = SidebarSessionsScreenViewModel(savedStateHandle, useCases)

            viewModel.state.test {
                val errorState = awaitItem()
                assertThat(errorState.selectedSession).isNull()
                testScheduler.advanceUntilIdle()
                coVerify { snackbarEvent(errorMessage) }
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `filtered sessions are applied correctly`() =
        runTest(testDispatchers.testDispatcher) {
            val filterString = "Morning"
            coEvery { useCases.getFilteredSessionsUseCase(filterString) } returns
                flow {
                    emit(Resource.Success(listOf(fakeSessions.first())))
                }

            viewModel = SidebarSessionsScreenViewModel(savedStateHandle, useCases)

            viewModel.state.test {
                awaitItem()
                viewModel.onEvent(SidebarSessionsScreenToViewModelEvents.FilterSessions(filterString))
                val filteredState = awaitItem()
                assertThat(filteredState.searchString).isEqualTo(filterString)
                val afterFilteredState = awaitItem()
                assertThat(afterFilteredState.sessions).isEqualTo(listOf(fakeSessions.first()))
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `filtered sessions error shows snackbar`() =
        runTest(testDispatchers.testDispatcher) {
            val filterString = "Morning"
            val errorMessage = MessageType.StringMessage("")
            coEvery { useCases.getFilteredSessionsUseCase(filterString) } returns
                flow {
                    emit(Resource.Error(errorMessage))
                }

            viewModel = SidebarSessionsScreenViewModel(savedStateHandle, useCases)

            viewModel.state.test {
                awaitItem()
                viewModel.onEvent(SidebarSessionsScreenToViewModelEvents.FilterSessions(filterString))
                val errorState = awaitItem()
                assertThat(errorState.searchString).isEqualTo(filterString)
                assertThat(errorState.sessions).isEmpty()
                testScheduler.advanceUntilIdle()
                coVerify { snackbarEvent(errorMessage) }
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `select session is executed successfully`() =
        runTest(testDispatchers.testDispatcher) {
            val sessionToSelect = fakeSessions.first()
            coEvery { useCases.selectSessionUseCase(sessionToSelect) } returns Resource.Success(true)

            viewModel = SidebarSessionsScreenViewModel(savedStateHandle, useCases)
            viewModel.onEvent(SidebarSessionsScreenToViewModelEvents.SelectSession(sessionToSelect))
            testScheduler.advanceUntilIdle()
            coVerify { useCases.selectSessionUseCase(sessionToSelect) }
        }

    @Test
    fun `select session error shows snackbar`() =
        runTest(testDispatchers.testDispatcher) {
            val sessionToSelect = fakeSessions.first()
            val errorMessage = MessageType.StringMessage("")
            coEvery { useCases.selectSessionUseCase(sessionToSelect) } returns
                Resource.Error(
                    errorMessage,
                )

            viewModel = SidebarSessionsScreenViewModel(savedStateHandle, useCases)
            viewModel.onEvent(SidebarSessionsScreenToViewModelEvents.SelectSession(sessionToSelect))
            testScheduler.advanceUntilIdle()
            coVerify { snackbarEvent(errorMessage) }
        }
}
