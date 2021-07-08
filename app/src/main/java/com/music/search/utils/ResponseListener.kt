package com.music.search.utils

interface ResponseListener {
    fun onLoading()
    fun onSuccess()
    fun onFailure(errorMessage: String)
}