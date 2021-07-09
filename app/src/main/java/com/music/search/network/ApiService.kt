package com.music.search.network

import com.music.search.model.SearchResultEntity
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    // Write Retrofit calls
    @GET
    fun getSearchResult(): Single<SearchResultEntity>
}