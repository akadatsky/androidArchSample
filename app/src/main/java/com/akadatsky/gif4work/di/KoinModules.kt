package com.akadatsky.gif4work.di

import com.akadatsky.gif4work.SERVER_URL
import com.akadatsky.gif4work.data.RepositoryImpl
import com.akadatsky.gif4work.data.remote.ApiService
import com.akadatsky.gif4work.presentation.Repository
import com.akadatsky.gif4work.presentation.gifslist.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//  presentation layer
val uiModule = module {
    viewModel { MainActivityViewModel() }
}

// data layer
val repoModule = module {
    single { RepositoryImpl(get()) } bind Repository::class
}

val networkModule = module {
    // ApiService
    single {
        Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
