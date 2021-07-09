package com.music.search.domain.data

import com.music.search.model.SearchResultEntity
import com.music.search.network.ApiService
import io.reactivex.Single

class SearchDataSourceImpl(private val apiService: ApiService) : ISearchDataSource {
    override fun getSearchResultAsync(): Single<SearchResultEntity> {
        return apiService.getSearchResult()
    }
}