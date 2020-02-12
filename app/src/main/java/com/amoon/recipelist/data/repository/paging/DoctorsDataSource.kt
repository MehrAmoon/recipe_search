package com.amoon.recipelist.data.repository.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.amoon.recipelist.data.ApiService
import com.amoon.recipelist.data.model.Drink
import com.amoon.recipelist.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

/*
Infinitely loading lists only download as much data as the user will immediately interact
with and then load more data on demand as the user scrolls farther in the list.
*/

class RecipeDataSource(
    private val keyword: String,
    private val apiService: ApiService,
    private val disposables: CompositeDisposable
) : PageKeyedDataSource<String, Drink>() {

    val networkError = MutableLiveData<NetworkState>()
    //load initial data
    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Drink>) {
        apiService
            .getRecipeList(keyword)
            ?.subscribe({
                callback.onResult(it.drinks, null, "")
            }, {
                networkError.postValue(NetworkState.error(it.message))
            })
            ?.addTo(disposables)
    }

    //load next data
    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Drink>) {
        apiService
            .getRecipeList(keyword)
            ?.subscribe({
                callback.onResult(it.drinks, params.key + 1)
            }, {
                networkError.postValue(NetworkState.error(it.message))
            })
            ?.addTo(disposables)
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Drink>) {}


}