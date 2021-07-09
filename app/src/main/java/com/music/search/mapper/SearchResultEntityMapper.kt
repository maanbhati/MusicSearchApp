package com.music.search.mapper

import com.music.search.domain.data.SearchResult
import com.music.search.model.SearchResultEntity

open class SearchResultEntityMapper : EntityMapper<SearchResultEntity, SearchResult>() {
    override fun mapFromRemote(type: SearchResultEntity): SearchResult {
        return SearchResult(type.totalResult)
    }
}