package com.music.search.domain.repository

import com.music.search.domain.data.SearchResult
import io.reactivex.Single

interface ISearchRepository {
    fun getSearchResult(): Single<SearchResult>
}