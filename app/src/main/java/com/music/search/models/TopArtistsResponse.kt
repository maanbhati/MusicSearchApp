package com.music.search.models

import com.google.gson.annotations.SerializedName

data class TopArtistsResponse(
    @SerializedName("artistmatches")
    var topartists: TopArtists
)