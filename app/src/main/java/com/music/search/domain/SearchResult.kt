package com.music.search.domain

import com.google.gson.annotations.SerializedName

data class SearchResult(@SerializedName("opensearch:totalResults") val totalResult: String)
