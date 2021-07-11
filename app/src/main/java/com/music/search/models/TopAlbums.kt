package com.music.search.models

import com.google.gson.annotations.SerializedName

data class TopAlbums(
    @SerializedName("album")
    var albums: MutableList<Album>
)