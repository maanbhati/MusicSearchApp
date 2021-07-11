package com.music.search.ui.artists

import android.util.Log
import com.music.search.models.Artist
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ArtistsPresenterImpl(var mView: ArtistsView, var mInteractor: ArtistsInteractor) :
    ArtistsPresenter {
    var mDisposable: Disposable? = null
    override fun onDestroy() {
        disposeRequest()
    }

    override fun getUserTopArtists(userName: String, apiKey: String) {
        Log.e("getUserTopArtists", "getting data for$userName")
        disposeRequest()
        mView.showProgress()
        mView.hidEmpty()
        mDisposable = mInteractor.getTopArtists(userName, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<List<Artist>> { topArtistsResponse ->
                topArtistsResponse.topartists.topartists.artists
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ artists ->
                mView.hideProgress()
                if (artists.isEmpty()) {
                    mView.showEmpty()
                }
                mView.updateData(artists as MutableList<Artist>)
            }) { mView.showError() }
    }

    private fun disposeRequest() {
        mDisposable?.let {
            if (!it.isDisposed)
                it.dispose()
        }
    }
}