package com.fernandohbrasil.marvelsquad.di

import com.fernandohbrasil.marvelsquad.datasource.db.AppDatabase
import com.fernandohbrasil.marvelsquad.datasource.IMarvelRepository
import com.fernandohbrasil.marvelsquad.datasource.MarvelRepository
import com.fernandohbrasil.marvelsquad.datasource.network.MarvelAPIFactory
import com.fernandohbrasil.marvelsquad.datasource.network.MarvelApi
import com.fernandohbrasil.marvelsquad.ui.viewmodel.activity.SharedViewModel
import com.fernandohbrasil.marvelsquad.ui.viewmodel.fragment.DetailViewModel
import com.fernandohbrasil.marvelsquad.ui.viewmodel.fragment.RootViewModel
import org.koin.dsl.module

val networkModule = module {
    factory { MarvelAPIFactory.marvelApi() }
}

val databaseModule = module {
    factory { AppDatabase.getInstance(get()) }
    single<IMarvelRepository> {
        MarvelRepository(
            get(),
            get()
        )
    }
}

val viewModelModule = module {
    single { RootViewModel(get()) }
    single { DetailViewModel(get()) }
    single { SharedViewModel() }
}

val repositoryModule = module {
    fun provideMarvelRepository(database: AppDatabase, api: MarvelApi): MarvelRepository {
        return MarvelRepository(database, api)
    }

    single { provideMarvelRepository(get(), get()) }
}