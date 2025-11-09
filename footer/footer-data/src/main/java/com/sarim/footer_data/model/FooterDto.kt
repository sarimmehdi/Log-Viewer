package com.sarim.footer_data.model

import com.sarim.footer_domain.model.Footer
import kotlinx.serialization.Serializable

@Serializable
data class FooterDto(
    val currentPageNum: Int,
    val maxResultsPerPage: Int,
) {
    companion object {
        val DEFAULT_FOOTER_DTO =
            FooterDto(
                currentPageNum = 1,
                maxResultsPerPage = 10,
            )

        fun FooterDto.toFooter() =
            Footer(
                currentPageNum = currentPageNum,
                maxResultsPerPage = maxResultsPerPage,
            )

        fun Footer.fromFooter() =
            FooterDto(
                currentPageNum = currentPageNum,
                maxResultsPerPage = maxResultsPerPage,
            )
    }
}
