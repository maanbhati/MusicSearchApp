package com.music.search.models

import com.google.gson.annotations.SerializedName

data class TopAlbumsResponse(
    @SerializedName("albummatches")
    var topAlbums: TopAlbums
)