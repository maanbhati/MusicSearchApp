package com.music.search.di

import com.music.search.domain.executer.JobExecutor
import com.music.search.domain.executer.UiThread
import com.music.search.interactors.GetSearchResult
import org.koin.dsl.module

val useCasesModule = module {
    factory { GetSearchResult(get(), JobExecutor(), UiThread()) }
}