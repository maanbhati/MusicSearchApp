package com.music.search.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.music.search.utils.Resource
import com.music.search.utils.ResponseListener
import io.reactivex.observers.DisposableSingleObserver

private const val TAG = "SearchResultViewModel"

class SearchResultViewModel(private val getSearchResult: String) : ViewModel() {

    init {
        Log.i(TAG, "created!")
    }

    val loadingError = MutableLiveData<String>()
    private val _breeds = MutableLiveData<Resource<List<String>>>()
    val breeds : LiveData<Resource<List<String>>>
        get() = _breeds
    lateinit var responseListener: ResponseListener

    init {
        loadingError.value = ""
        getSearchResult()
    }

    fun getSearchResult() {
       // getSearchResult.execute(SearchResultSubscriber())
    }


    private inner class SearchResultSubscriber : DisposableSingleObserver<String>() {
        override fun onSuccess(albumName: String) {
          //  _breeds.value = Resource.Success(breeds.value)
        }

        override fun onError(e: Throwable) {
            _breeds.value = Resource.Error(e.message)
            loadingError.postValue(e.message)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(TAG, "destroyed!")
       // getSearchResult.dispose()
    }
}