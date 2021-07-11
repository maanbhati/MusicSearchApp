package com.music.search.models

import com.google.gson.annotations.SerializedName

data class TopArtists(
    @SerializedName("artist")
    var artists: MutableList<Artist>
)