package com.music.search.models

import com.google.gson.annotations.SerializedName

data class TopTracksResponseResult(
    @SerializedName("results")
    var topTracks: TopTracksResponse
)