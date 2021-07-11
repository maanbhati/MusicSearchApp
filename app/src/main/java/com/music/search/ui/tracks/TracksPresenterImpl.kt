package com.music.search.ui.tracks

import com.music.search.models.Track
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class TracksPresenterImpl(var mView: TracksView, var mInteractor: TracksInteractor) :
    TracksPresenter {
    var mDisposable: Disposable? = null

    override fun getTracks(userName: String, apiKey: String) {
        disposeRequest()
        mView.showProgress()
        mView.hidEmpty()
        mDisposable = mInteractor.getTopTracks(userName, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<MutableList<Track>> { topTracksResponse ->
                topTracksResponse.topTracks.topTracks.tracks
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ tracks ->
                mView.hideProgress()
                if (tracks.size == 0) {
                    mView.showEmpty()
                }
                mView.updateData(tracks)
            }) {
                mView.hideProgress()
                mView.showError()
            }
    }

    override fun onDestroy() {
        disposeRequest()
    }

    private fun disposeRequest() {
        mDisposable?.let {
            if (!it.isDisposed)
                it.dispose()
        }
    }
}