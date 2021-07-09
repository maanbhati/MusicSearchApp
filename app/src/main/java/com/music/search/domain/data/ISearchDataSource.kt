package com.music.search.domain.data

import com.music.search.model.SearchResultEntity
import io.reactivex.Single

interface ISearchDataSource {
    fun getSearchResultAsync(): Single<SearchResultEntity>
}