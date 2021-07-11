package com.music.search.models

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("name")
    var name: String,

    @SerializedName("artist")
    var artist: String,

    @SerializedName("url")
    var url: String,

    @SerializedName("image")
    var image: List<ImageItem>,

    @SerializedName("streamable")
    var streamable: String,

    @SerializedName("mbid")
    var mbid: String

) {
    val imageUrl: String?
        get() {
            if (image.isNotEmpty()) {
                for (img in image) {
                    if (img.size.equals("large", ignoreCase = true)) {
                        return img.url
                    }
                }
            }
            return null
        }
}
