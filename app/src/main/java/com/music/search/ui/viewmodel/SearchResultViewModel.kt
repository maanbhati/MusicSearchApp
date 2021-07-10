package com.music.search.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.music.search.domain.data.SearchResult
import com.music.search.interactors.GetSearchResult
import com.music.search.utils.Resource
import com.music.search.utils.ResponseListener
import io.reactivex.observers.DisposableSingleObserver

private const val TAG = "SearchResultViewModel"

class SearchResultViewModel(private val getSearchResult: GetSearchResult) : ViewModel() {

    val loadingError = MutableLiveData<String>()
    private val _totalResult = MutableLiveData<Resource<String>>()
    val totalResult: LiveData<Resource<String>>
        get() = _totalResult
    lateinit var responseListener: ResponseListener

    init {
        loadingError.value = ""
        getSearchResult()
    }

    private fun getSearchResult() {
        getSearchResult.execute(SearchResultSubscriber())
    }

    private inner class SearchResultSubscriber : DisposableSingleObserver<SearchResult>() {
        override fun onSuccess(searchResult: SearchResult) {
            _totalResult.value = Resource.Success(searchResult.totalResult)
        }

        override fun onError(e: Throwable) {
            _totalResult.value = Resource.Error(e.message)
            loadingError.postValue(e.message)
        }
    }

    override fun onCleared() {
        Log.i(TAG, "destroyed!")
        getSearchResult.dispose()
    }
}