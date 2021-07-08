package com.music.search.presentation

import androidx.lifecycle.ViewModel
import android.util.Log

private const val TAG = "SearchViewModel"

class SearchViewModel() : ViewModel() {

    init {
        Log.i(TAG, "created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "destroyed!")
    }
}