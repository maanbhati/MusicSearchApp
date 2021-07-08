package com.music.search.di

import com.music.search.ui.viewmodel.SearchResultViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchResultViewModel(get()) }
}