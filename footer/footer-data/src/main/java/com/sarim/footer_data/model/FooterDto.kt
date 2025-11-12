package com.sarim.footer_data.model

import com.sarim.footer_domain.model.Footer
import kotlinx.serialization.Serializable

@Serializable
data class FooterDto(
    val maxResultsPerPage: Int,
) {
    companion object {
        val DEFAULT_FOOTER_DTO =
            FooterDto(
                maxResultsPerPage = 20,
            )
    }
}

fun createFooter(footerDto: FooterDto) =
    Footer(
        maxResultsPerPage = footerDto.maxResultsPerPage,
    )
