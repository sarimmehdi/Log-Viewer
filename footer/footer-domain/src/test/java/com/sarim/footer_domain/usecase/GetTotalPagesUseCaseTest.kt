package com.sarim.footer_domain.usecase

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.api.Assertions.assertEquals

class GetTotalPagesUseCaseTest {
    private val useCase = GetTotalPagesUseCase()

    @ParameterizedTest
    @MethodSource("totalPagesProvider")
    fun `calculate total pages correctly`(case: TestCase) {
        val result = useCase(case.totalLogs, case.logsPerPage)
        assertEquals(case.expected, result)
    }

    data class TestCase(
        val totalLogs: Int,
        val logsPerPage: Int,
        val expected: Int,
    )

    companion object {
        @JvmStatic
        fun totalPagesProvider() =
            listOf(
                TestCase(totalLogs = 0, logsPerPage = 10, expected = 0),
                TestCase(totalLogs = 5, logsPerPage = 10, expected = 1),
                TestCase(totalLogs = 10, logsPerPage = 10, expected = 1),
                TestCase(totalLogs = 11, logsPerPage = 10, expected = 2),
                TestCase(totalLogs = 25, logsPerPage = 10, expected = 3),
                TestCase(totalLogs = 10, logsPerPage = 0, expected = 0),
            )
    }
}
