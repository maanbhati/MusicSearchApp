package com.music.search.models

import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("mbid")
    var mbid: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("image")
    var images: List<ImageItem>,

    @SerializedName("listeners")
    var playcount: String,

    @SerializedName("artist")
    var artist: String,

    @SerializedName("url")
    var url: String
) {
    fun setImage(images: MutableList<ImageItem>) {
        this.images = images
    }

    val imageUrl: String?
        get() {
            if (images.isNotEmpty()) {
                for (img in images) {
                    if (img.size.equals("large", ignoreCase = true)) {
                        return img.url
                    }
                }
            }
            return null
        }
}