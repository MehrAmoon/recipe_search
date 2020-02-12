package com.amoon.recipelist.data.repository

import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.amoon.recipelist.data.ApiService
import com.amoon.recipelist.data.model.Drink
import com.amoon.recipelist.data.repository.paging.RecipeDataSourceFactory
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AppDataRepository @Inject constructor(
    private val apiService: ApiService,
    private val disposables: CompositeDisposable
): AppRepository {


    override fun getRecipeList(keyword: String): Single<Listing<Drink>> {
        val dataSourceFactory = RecipeDataSourceFactory(keyword, apiService, disposables)


        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(false)
            .build()

        val networkError = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
            it.networkError
        }


        return Single.just(Listing(
            LivePagedListBuilder(dataSourceFactory, config).build(),
            networkError
        ))
    }

}