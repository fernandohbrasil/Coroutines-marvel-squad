package com.fernandohbrasil.marvelsquad.ui.paging

import androidx.paging.DataSource
import com.fernandohbrasil.marvelsquad.datasource.MarvelRepository
import com.fernandohbrasil.marvelsquad.datasource.network.model.Character
import io.reactivex.disposables.CompositeDisposable

class CharactersDataSourceFactory(
    private val repository: MarvelRepository,
    private val disposable: CompositeDisposable
) :
    DataSource.Factory<Int, Character>() {

    override fun create(): DataSource<Int, Character> {
        return CharactersDataSource(repository, disposable)
    }
}
