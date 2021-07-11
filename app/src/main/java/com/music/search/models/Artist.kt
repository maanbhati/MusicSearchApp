package com.music.search.models

import com.google.gson.annotations.SerializedName

data class Artist(
    @SerializedName("mbid")
    var mbid: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("image")
    var images: List<ImageItem>,

    @SerializedName("streamable")
    var streamable: String,

    @SerializedName("playcount")
    var playcount: String = "",

    @SerializedName("url")
    var url: String
) {

    fun setImage(images: List<ImageItem>) {
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