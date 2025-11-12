package com.sarim.footer_domain.repository

import com.sarim.footer_domain.model.Footer
import com.sarim.utils.ui.Resource
import kotlinx.coroutines.flow.Flow

interface FooterRepository {
    val footer: Flow<Resource<Footer>>
}
