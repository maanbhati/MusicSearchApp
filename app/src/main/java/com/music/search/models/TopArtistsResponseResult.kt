package com.music.search.models

import com.google.gson.annotations.SerializedName

data class TopArtistsResponseResult(
    @SerializedName("results")
    var topartists: TopArtistsResponse
)