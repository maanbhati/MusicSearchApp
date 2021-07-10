package com.music.search.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.music.search.network.ApiService
import com.music.search.utils.BASE_SEARCH_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


var logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

var client = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()

val apiModule = module {
    val gson: Gson = GsonBuilder()
        .enableComplexMapKeySerialization()
        .serializeNulls()
        .setPrettyPrinting()
        .create()

    fun provideApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_SEARCH_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiService::class.java)
    }

    single { provideApi() }
}