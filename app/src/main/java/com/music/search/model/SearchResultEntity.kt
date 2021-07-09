package com.music.search.model

import com.google.gson.annotations.SerializedName

class SearchResultEntity(@SerializedName("opensearch:totalResults") val totalResult: String)