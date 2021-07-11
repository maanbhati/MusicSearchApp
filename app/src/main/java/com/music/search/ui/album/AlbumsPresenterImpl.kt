package com.music.search.ui.album

import com.music.search.models.Album
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AlbumsPresenterImpl(var mView: AlbumsView, var mInteractor: AlbumsInteractor) :
    AlbumsPresenter {
    var mDisposable: Disposable? = null

    override fun getAlbums(userName: String, apiKey: String) {
        mView.showProgress()
        mView.hidEmpty()
        disposeRequest()
        mDisposable = mInteractor.getTopAlbums(userName, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<MutableList<Album>> { topAlbumsResponse ->
                topAlbumsResponse.topAlbums.topAlbums.albums
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ albums ->
                mView.hideProgress()
                if (albums.size == 0) {
                    mView.showEmpty()
                }
                mView.updateData(albums)
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