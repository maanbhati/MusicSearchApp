package com.music.search.presentation

import androidx.lifecycle.ViewModel
import android.util.Log

private const val TAG = "SearchViewModel"

class SearchViewModel() : ViewModel() {

    init {
        Log.i(TAG, "created!")
    }

    open class OnClickListener(val clickListener: (searchKeyword: String) -> Unit) {
        fun onClick(searchKeyword: String) = clickListener(searchKeyword)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "destroyed!")
    }
}