package com.sarim.footer_domain.usecase

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.api.Assertions.assertEquals

class ChangeCurrentPageNumUseCaseTest {
    private val useCase = ChangeCurrentPageNumUseCase()

    data class TestCase(
        val changeType: ChangeType,
        val curr: Int,
        val total: Int,
        val expected: Int,
    )

    @ParameterizedTest
    @MethodSource("changeTypeProvider")
    fun `invoke with changeType behaves correctly`(case: TestCase) {
        val result = useCase(case.changeType, case.curr, case.total)
        assertEquals(case.expected, result)
    }

    companion object {
        @JvmStatic
        fun changeTypeProvider() =
            listOf(
                TestCase(ChangeType.INCREASE, 1, 5, 2),
                TestCase(ChangeType.INCREASE, 5, 5, 5),
                TestCase(ChangeType.DECREASE, 5, 5, 4),
                TestCase(ChangeType.DECREASE, 1, 5, 1),
            )
    }
}
