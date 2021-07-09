package com.music.search.domain.repository

import com.music.search.domain.data.ISearchDataSource
import com.music.search.domain.data.SearchResult
import com.music.search.mapper.SearchResultEntityMapper
import io.reactivex.Single

class SearchRepository(
    private val dataSource: ISearchDataSource,
    private val entityMapper: SearchResultEntityMapper
) : ISearchRepository {
    override fun getSearchResult(): Single<SearchResult> {
        return dataSource.getSearchResultAsync().map {
            entityMapper.mapFromRemote(it)
        }
    }
}