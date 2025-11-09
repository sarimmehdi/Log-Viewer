package com.sarim.footer_domain.usecase

class GetPageInfoUseCase {
    fun getStartingLogIndexOnCurrPage(
        currPage: Int,
        logsPerPage: Int,
    ) = (currPage - 1) * logsPerPage + 1

    fun getEndingLogIndexOnCurrPage(
        currPage: Int,
        logsPerPage: Int,
    ) = currPage * logsPerPage

    fun canGoToNextPage(
        currPage: Int,
        totalPages: Int,
    ) = currPage < totalPages

    fun canGoToPreviousPage(currPage: Int) = currPage > 1
}
