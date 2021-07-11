package com.music.search.models

import com.google.gson.annotations.SerializedName

data class TopTracksResponse(
    @SerializedName("trackmatches")
    var topTracks: TopTracks
)