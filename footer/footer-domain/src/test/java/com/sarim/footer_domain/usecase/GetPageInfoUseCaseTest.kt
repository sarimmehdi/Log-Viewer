package com.sarim.footer_domain.usecase

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.api.Assertions.assertEquals

class GetPageInfoUseCaseTest {
    private val useCase = GetPageInfoUseCase()

    @ParameterizedTest
    @MethodSource("indexProvider")
    fun `starting and ending log index are correct`(case: IndexTestCase) {
        val start = useCase.getStartingLogIndexOnCurrPage(case.currPage, case.logsPerPage)
        val end = useCase.getEndingLogIndexOnCurrPage(case.currPage, case.logsPerPage)
        assertEquals(case.expectedStart, start)
        assertEquals(case.expectedEnd, end)
    }

    @ParameterizedTest
    @MethodSource("nextProvider")
    fun `can go to next page works`(case: NextPrevTestCase) {
        val result = useCase.canGoToNextPage(case.currPage, case.totalPages)
        assertEquals(case.expected, result)
    }

    @ParameterizedTest
    @MethodSource("prevProvider")
    fun `can go to previous page works`(case: NextPrevTestCase) {
        val result = useCase.canGoToPreviousPage(case.currPage)
        assertEquals(case.expected, result)
    }

    data class IndexTestCase(
        val currPage: Int,
        val logsPerPage: Int,
        val expectedStart: Int,
        val expectedEnd: Int,
    )

    data class NextPrevTestCase(
        val currPage: Int,
        val totalPages: Int,
        val expected: Boolean,
    )

    companion object {
        @JvmStatic
        fun indexProvider() =
            listOf(
                IndexTestCase(currPage = 1, logsPerPage = 10, expectedStart = 1, expectedEnd = 10),
                IndexTestCase(currPage = 2, logsPerPage = 10, expectedStart = 11, expectedEnd = 20),
                IndexTestCase(currPage = 5, logsPerPage = 5, expectedStart = 21, expectedEnd = 25),
            )

        @JvmStatic
        fun nextProvider() =
            listOf(
                NextPrevTestCase(currPage = 1, totalPages = 5, expected = true),
                NextPrevTestCase(currPage = 5, totalPages = 5, expected = false),
                NextPrevTestCase(currPage = 3, totalPages = 5, expected = true),
            )

        @JvmStatic
        fun prevProvider() =
            listOf(
                NextPrevTestCase(currPage = 1, totalPages = 5, expected = false),
                NextPrevTestCase(currPage = 2, totalPages = 5, expected = true),
                NextPrevTestCase(currPage = 5, totalPages = 5, expected = true),
            )
    }
}
