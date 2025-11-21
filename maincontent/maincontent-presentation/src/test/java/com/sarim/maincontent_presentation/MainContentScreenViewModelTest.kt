package com.sarim.maincontent_presentation

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.sarim.footer_domain.model.Footer
import com.sarim.footer_domain.usecase.ChangeCurrentPageNumUseCase
import com.sarim.footer_presentation.FooterScreenToViewModelEvents
import com.sarim.footer_presentation.FooterScreenUseCases
import com.sarim.header_presentation.HeaderScreenState.DropDownType
import com.sarim.header_presentation.HeaderScreenToViewModelEvents
import com.sarim.maincontent_domain.model.LogMessage
import com.sarim.maincontent_domain.model.LogMessage.LogType
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
internal class MainContentScreenViewModelTest {
    private val testDispatchers = TestDispatchers()
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var useCases: MainContentScreenUseCases
    private lateinit var footerScreenUseCases: FooterScreenUseCases
    private lateinit var viewModel: MainContentScreenViewModel

    private val fakeLogs =
        listOf(
            LogMessage(
                logMessageId = 1L,
                sessionId = 1L,
                message = "Test error message",
                className = "TestClass",
                functionName = "testFunction",
                lineNumber = 42,
                logType = LogType.ERROR,
            ),
            LogMessage(
                logMessageId = 2L,
                sessionId = 1L,
                message = "Test debug message",
                className = "TestClass",
                functionName = "debugFunction",
                lineNumber = 100,
                logType = LogType.DEBUG,
            ),
        )

    @BeforeEach
    fun setup() {
        mockkObject(SnackBarController)
        savedStateHandle = SavedStateHandle()
        footerScreenUseCases = mockk(relaxed = true)
        useCases = mockk(relaxed = true)
        every { useCases.footerScreenUseCases } returns footerScreenUseCases
        every { useCases.getLogMessagesUseCase(any()) } returns emptyFlow()
        every { useCases.getTotalLogMessagesNumUseCase() } returns emptyFlow()
        coEvery { useCases.getFilteredLogsUseCase(any()) } returns emptyFlow()
        every { footerScreenUseCases.getFooterUseCase() } returns emptyFlow()
        every { footerScreenUseCases.getSelectedSessionUseCase() } returns emptyFlow()
        every { footerScreenUseCases.getTotalPagesUseCase(any(), any()) } returns 1
        every {
            footerScreenUseCases.getPageInfoUseCase.getStartingLogIndexOnCurrPage(
                any(),
                any(),
            )
        } returns 1
        every {
            footerScreenUseCases.getPageInfoUseCase.getEndingLogIndexOnCurrPage(
                any(),
                any(),
            )
        } returns 10
        every {
            footerScreenUseCases.getPageInfoUseCase.canGoToNextPage(
                any(),
                any(),
            )
        } returns false
        every { footerScreenUseCases.getPageInfoUseCase.canGoToPreviousPage(any()) } returns false
        coEvery { footerScreenUseCases.changeCurrentPageNumUseCase(any<Int>(), any()) } returns 1
        coEvery {
            footerScreenUseCases.changeCurrentPageNumUseCase(
                any<ChangeCurrentPageNumUseCase.ChangeType>(),
                any(),
                any(),
            )
        } returns 1
    }

    @Test
    fun `logs are loaded successfully`() =
        runTest(testDispatchers.testDispatcher) {
            coEvery { useCases.getLogMessagesUseCase(any()) } returns
                flow {
                    emit(Resource.Success(fakeLogs))
                }

            viewModel = MainContentScreenViewModel(testDispatchers, savedStateHandle, useCases)

            viewModel.state.test {
                val initialState = awaitItem()
                assertThat(initialState.logs).isEmpty()

                val loadedState = awaitItem()
                assertThat(loadedState.logs).isEqualTo(fakeLogs)

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `logs load error triggers snackbar`() =
        runTest(testDispatchers.testDispatcher) {
            val errorMessage = MessageType.StringMessage("")

            coEvery { useCases.getLogMessagesUseCase(any()) } returns
                flow {
                    emit(Resource.Error(errorMessage))
                }

            viewModel = MainContentScreenViewModel(testDispatchers, savedStateHandle, useCases)

            viewModel.state.test {
                val errorState = awaitItem()
                assertThat(errorState.logs).isEmpty()
                testScheduler.advanceUntilIdle()
                coVerify { snackbarEvent(errorMessage) }
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `total log number and page count are loaded successfully`() =
        runTest(testDispatchers.testDispatcher) {
            val totalLogs = 45
            val maxResultsPerPage = 10
            every { useCases.getTotalLogMessagesNumUseCase() } returns
                flow {
                    emit(Resource.Success(totalLogs))
                }
            every {
                footerScreenUseCases.getTotalPagesUseCase(
                    totalLogs,
                    maxResultsPerPage,
                )
            } returns 5

            viewModel = MainContentScreenViewModel(testDispatchers, savedStateHandle, useCases)

            viewModel.state.test {
                awaitItem()
                testScheduler.advanceUntilIdle()
                val loadedState = awaitItem()
                testScheduler.advanceUntilIdle()
                assertThat(loadedState.footerScreenState.totalLogs).isEqualTo(totalLogs)
                assertThat(loadedState.footerScreenState.totalPages).isEqualTo(5)
            }
        }

    @Test
    fun `total log number load error triggers snackbar`() =
        runTest(testDispatchers.testDispatcher) {
            val errorMessage = MessageType.StringMessage("")
            every { useCases.getTotalLogMessagesNumUseCase() } returns
                flow {
                    emit(Resource.Error(errorMessage))
                }

            viewModel = MainContentScreenViewModel(testDispatchers, savedStateHandle, useCases)

            viewModel.state.test {
                val errorState = awaitItem()
                assertThat(errorState.footerScreenState.totalLogs).isEqualTo(0)
                assertThat(errorState.footerScreenState.totalPages).isEqualTo(1)
                testScheduler.advanceUntilIdle()
                coVerify { snackbarEvent(errorMessage) }
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `footer info is loaded successfully`() =
        runTest(testDispatchers.testDispatcher) {
            val fakeFooter = Footer(maxResultsPerPage = 20)
            every { footerScreenUseCases.getFooterUseCase() } returns
                flow {
                    emit(
                        Resource.Success(
                            fakeFooter,
                        ),
                    )
                }
            every {
                footerScreenUseCases.getPageInfoUseCase.getStartingLogIndexOnCurrPage(
                    any(),
                    any(),
                )
            } returns 9
            every {
                footerScreenUseCases.getPageInfoUseCase.getEndingLogIndexOnCurrPage(
                    any(),
                    any(),
                )
            } returns 100
            every {
                footerScreenUseCases.getPageInfoUseCase.canGoToNextPage(
                    any(),
                    any(),
                )
            } returns true
            every {
                footerScreenUseCases.getPageInfoUseCase.canGoToPreviousPage(
                    any(),
                )
            } returns true

            viewModel = MainContentScreenViewModel(testDispatchers, savedStateHandle, useCases)

            viewModel.state.test {
                val initialState = awaitItem()
                assertThat(initialState.footerScreenState.maxResultsPerPage).isEqualTo(10)
                assertThat(initialState.footerScreenState.currentPageNum).isEqualTo(1)
                assertThat(initialState.footerScreenState.numFirstLogOnCurrPage).isEqualTo(1)
                assertThat(initialState.footerScreenState.numLastLogOnCurrPage).isEqualTo(1)
                assertThat(initialState.footerScreenState.canGoToNextPage).isEqualTo(false)
                assertThat(initialState.footerScreenState.canGoToPreviousPage).isEqualTo(false)
                assertThat(initialState.headerScreenState.searchString).isEqualTo("")

                testScheduler.advanceUntilIdle()

                val loadedState = awaitItem()
                assertThat(loadedState.footerScreenState.maxResultsPerPage).isEqualTo(fakeFooter.maxResultsPerPage)
                assertThat(loadedState.footerScreenState.currentPageNum).isEqualTo(1)
                assertThat(loadedState.footerScreenState.numFirstLogOnCurrPage).isEqualTo(9)
                assertThat(loadedState.footerScreenState.numLastLogOnCurrPage).isEqualTo(100)
                assertThat(loadedState.footerScreenState.canGoToNextPage).isEqualTo(true)
                assertThat(loadedState.footerScreenState.canGoToPreviousPage).isEqualTo(true)
                assertThat(loadedState.headerScreenState.searchString).isEqualTo("")

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `footer info load error triggers snackbar`() =
        runTest(testDispatchers.testDispatcher) {
            val errorMessage = MessageType.StringMessage("")
            every { footerScreenUseCases.getFooterUseCase() } returns
                flow {
                    emit(
                        Resource.Error(
                            errorMessage,
                        ),
                    )
                }

            viewModel = MainContentScreenViewModel(testDispatchers, savedStateHandle, useCases)

            viewModel.state.test {
                val errorState = awaitItem()
                assertThat(errorState.footerScreenState.maxResultsPerPage).isEqualTo(10)
                assertThat(errorState.footerScreenState.currentPageNum).isEqualTo(1)
                assertThat(errorState.footerScreenState.numFirstLogOnCurrPage).isEqualTo(1)
                assertThat(errorState.footerScreenState.numLastLogOnCurrPage).isEqualTo(1)
                assertThat(errorState.footerScreenState.canGoToNextPage).isEqualTo(false)
                assertThat(errorState.footerScreenState.canGoToPreviousPage).isEqualTo(false)
                assertThat(errorState.headerScreenState.searchString).isEqualTo("")
                testScheduler.advanceUntilIdle()
                coVerify { snackbarEvent(errorMessage) }
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `selected session is loaded successfully`() =
        runTest(testDispatchers.testDispatcher) {
            val fakeSession = mockk<Session>()
            every { footerScreenUseCases.getSelectedSessionUseCase() } returns
                flow {
                    emit(
                        Resource.Success(
                            fakeSession,
                        ),
                    )
                }

            viewModel = MainContentScreenViewModel(testDispatchers, savedStateHandle, useCases)

            viewModel.state.test {
                val initialState = awaitItem()
                assertThat(initialState.footerScreenState.selectedSession).isNull()
                assertThat(initialState.footerScreenState.currentPageNum).isEqualTo(1)
                assertThat(initialState.headerScreenState.searchString).isEqualTo("")

                testScheduler.advanceUntilIdle()

                val loadedState = awaitItem()
                assertThat(loadedState.footerScreenState.selectedSession).isEqualTo(fakeSession)
                assertThat(loadedState.footerScreenState.currentPageNum).isEqualTo(1)
                assertThat(loadedState.headerScreenState.searchString).isEqualTo("")

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `selected session load error triggers snackbar`() =
        runTest(testDispatchers.testDispatcher) {
            val errorMessage = MessageType.StringMessage("")
            every { footerScreenUseCases.getSelectedSessionUseCase() } returns
                flow {
                    emit(
                        Resource.Error(
                            errorMessage,
                        ),
                    )
                }

            viewModel = MainContentScreenViewModel(testDispatchers, savedStateHandle, useCases)

            viewModel.state.test {
                val errorState = awaitItem()
                assertThat(errorState.footerScreenState.selectedSession).isNull()
                assertThat(errorState.footerScreenState.currentPageNum).isEqualTo(1)
                assertThat(errorState.headerScreenState.searchString).isEqualTo("")
                testScheduler.advanceUntilIdle()
                coVerify { snackbarEvent(errorMessage) }
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `getFilteredLogs loads full logs when search string is empty`() =
        runTest(testDispatchers.testDispatcher) {
            coEvery { useCases.getLogMessagesUseCase(any()) } returns
                flow {
                    emit(
                        Resource.Success(
                            fakeLogs,
                        ),
                    )
                }

            viewModel = MainContentScreenViewModel(testDispatchers, savedStateHandle, useCases)

            viewModel.state.test {
                val initialState = awaitItem()
                assertThat(initialState.logs).isEmpty()

                testScheduler.advanceUntilIdle()

                val loadedState = awaitItem()
                assertThat(loadedState.logs).isEqualTo(fakeLogs)

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `getFilteredLogs loads filtered logs when search string is non-empty`() =
        runTest(testDispatchers.testDispatcher) {
            val filteredLogs = listOf(fakeLogs[1])
            coEvery { useCases.getFilteredLogsUseCase("debug") } returns
                flow {
                    emit(
                        Resource.Success(
                            filteredLogs,
                        ),
                    )
                }

            viewModel = MainContentScreenViewModel(testDispatchers, savedStateHandle, useCases)
            viewModel.onEvent(
                MainContentScreenToViewModelEvents.HeaderEvent(
                    event =
                        HeaderScreenToViewModelEvents.FilterLogs(
                            text = "debug",
                        ),
                ),
            )

            viewModel.state.test {
                val initialState = awaitItem()
                assertThat(initialState.logs).isEmpty()

                testScheduler.advanceUntilIdle()

                awaitItem()
                val loadedState = awaitItem()
                assertThat(loadedState.logs).isEqualTo(filteredLogs)

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `getFilteredLogs triggers snackbar on error`() =
        runTest(testDispatchers.testDispatcher) {
            val errorMessage = MessageType.StringMessage("")
            coEvery { useCases.getLogMessagesUseCase(any()) } returns
                flow {
                    emit(
                        Resource.Error(
                            errorMessage,
                        ),
                    )
                }

            viewModel = MainContentScreenViewModel(testDispatchers, savedStateHandle, useCases)

            viewModel.state.test {
                val errorState = awaitItem()
                assertThat(errorState.logs).isEmpty()
                testScheduler.advanceUntilIdle()
                coVerify { snackbarEvent(errorMessage) }
                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `onFooterEvent ChangeCurrentPageNumber updates currentPageNum and resets search string`() =
        runTest(testDispatchers.testDispatcher) {
            coEvery { footerScreenUseCases.changeCurrentPageNumUseCase(3, any()) } returns 3

            viewModel = MainContentScreenViewModel(testDispatchers, savedStateHandle, useCases)

            viewModel.onEvent(
                MainContentScreenToViewModelEvents.FooterEvent(
                    FooterScreenToViewModelEvents.ChangeCurrentPageNumber(
                        pageNumber = 3,
                    ),
                ),
            )

            viewModel.state.test {
                val initial = awaitItem()
                assertThat(initial.footerScreenState.currentPageNum).isEqualTo(1)
                assertThat(initial.headerScreenState.searchString).isEqualTo("")

                testScheduler.advanceUntilIdle()

                val updated = awaitItem()
                assertThat(updated.footerScreenState.currentPageNum).isEqualTo(3)
                assertThat(updated.headerScreenState.searchString).isEqualTo("")

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `onFooterEvent ChangeCurrentPageNumberByOne updates page number and resets search string`() =
        runTest(testDispatchers.testDispatcher) {
            coEvery {
                footerScreenUseCases.changeCurrentPageNumUseCase(
                    ChangeCurrentPageNumUseCase.ChangeType.INCREASE,
                    any(),
                    any(),
                )
            } returns 2

            viewModel = MainContentScreenViewModel(testDispatchers, savedStateHandle, useCases)

            viewModel.onEvent(
                MainContentScreenToViewModelEvents.FooterEvent(
                    FooterScreenToViewModelEvents.ChangeCurrentPageNumberByOne(
                        changeType = ChangeCurrentPageNumUseCase.ChangeType.INCREASE,
                    ),
                ),
            )

            viewModel.state.test {
                val initial = awaitItem()
                assertThat(initial.footerScreenState.currentPageNum).isEqualTo(1)
                assertThat(initial.headerScreenState.searchString).isEqualTo("")

                testScheduler.advanceUntilIdle()

                val updated = awaitItem()
                assertThat(updated.footerScreenState.currentPageNum).isEqualTo(2)
                assertThat(updated.headerScreenState.searchString).isEqualTo("")

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `onHeaderEvent FilterLogs updates search string`() =
        runTest(testDispatchers.testDispatcher) {
            viewModel = MainContentScreenViewModel(testDispatchers, savedStateHandle, useCases)

            viewModel.onEvent(
                MainContentScreenToViewModelEvents.HeaderEvent(
                    HeaderScreenToViewModelEvents.FilterLogs(text = "abc"),
                ),
            )

            viewModel.state.test {
                val initial = awaitItem()
                assertThat(initial.headerScreenState.searchString).isEqualTo("")

                testScheduler.advanceUntilIdle()

                val updated = awaitItem()
                assertThat(updated.headerScreenState.searchString).isEqualTo("abc")

                cancelAndIgnoreRemainingEvents()
            }
        }

    @Test
    fun `onHeaderEvent ChangeDropDownType updates dropDownType`() =
        runTest(testDispatchers.testDispatcher) {
            val newType = mockk<DropDownType>(relaxed = true)
            viewModel = MainContentScreenViewModel(testDispatchers, savedStateHandle, useCases)

            viewModel.onEvent(
                MainContentScreenToViewModelEvents.HeaderEvent(
                    HeaderScreenToViewModelEvents.CangeDropDownType(
                        dropDownType = newType,
                    ),
                ),
            )

            viewModel.state.test {
                val initial = awaitItem()
                assertThat(initial.headerScreenState.dropDownType).isNotEqualTo(newType)

                testScheduler.advanceUntilIdle()

                val updated = awaitItem()
                assertThat(updated.headerScreenState.dropDownType).isEqualTo(newType)

                cancelAndIgnoreRemainingEvents()
            }
        }
}
