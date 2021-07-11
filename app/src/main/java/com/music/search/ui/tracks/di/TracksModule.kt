package com.music.search.ui.tracks.di

import com.music.search.ui.tracks.*
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
class TracksModule(var mView: TracksView) {

    // provides the view to create the top tracks presenter
    @Singleton
    @Provides
    fun providesTopTracksView(): TracksView {
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

    // provides a retrofit instance to create the top tracks interactor
    @Singleton
    @Provides
    fun providesRetrofit(converter: Converter.Factory, adapter: CallAdapter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(adapter)
            .addConverterFactory(converter)
            .build()
    }

    // provides top tracks interactor to make an instance of the presenter
    @Singleton
    @Provides
    fun providesTopTopTracksInteractor(retrofit: Retrofit): TracksInteractor {
        return TracksInteractorImpl(retrofit)
    }

    // provides top albums presenter
    @Singleton
    @Provides
    fun providesTopTracksPresenter(
        view: TracksView,
        interactor: TracksInteractor
    ): TracksPresenter {
        return TracksPresenterImpl(view, interactor)
    }
}