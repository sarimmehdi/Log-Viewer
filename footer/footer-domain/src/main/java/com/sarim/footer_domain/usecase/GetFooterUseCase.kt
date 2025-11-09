package com.sarim.footer_domain.usecase

import com.sarim.footer_domain.repository.FooterRepository

class GetFooterUseCase(
    val repository: FooterRepository,
) {
    operator fun invoke() = repository.footer
}
