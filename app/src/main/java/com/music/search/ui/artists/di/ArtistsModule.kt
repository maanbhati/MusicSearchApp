package com.music.search.ui.artists.di

import android.util.Log
import com.music.search.ui.artists.*
import com.music.search.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ArtistsModule(var mView: ArtistsView) {

    // provides the view to create the top artists presenter
    @Singleton
    @Provides
    fun providesTopArtistsView(): ArtistsView {
        return mView
    }

    // provides a converter factory to create the retrofit instance
    @Singleton
    @Provides
    fun providesConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    // provides a call adapter factory needed to integrate rxjava with retrofit
    @Singleton
    @Provides
    fun providesCallAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.create()
    }

    // provides a retrofit instance to create the top artists interactor
    @Singleton
    @Provides
    fun providesRetrofit(converter: Converter.Factory, adapter: CallAdapter.Factory): Retrofit {
        Log.d("url_str", adapter.toString())
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(adapter)
            .addConverterFactory(converter)
            .build()
    }

    // provides top artists interactor to make an instance of the presenter
    @Singleton
    @Provides
    fun providesTopArtistsInteractor(retrofit: Retrofit): ArtistsInteractor {
        return ArtistsInteractorImpl(retrofit)
    }

    // provides top artists presenter
    @Singleton
    @Provides
    fun providesTopArtistsPresenter(
        view: ArtistsView,
        interactor: ArtistsInteractor
    ): ArtistsPresenter {
        return ArtistsPresenterImpl(view, interactor)
    }
}