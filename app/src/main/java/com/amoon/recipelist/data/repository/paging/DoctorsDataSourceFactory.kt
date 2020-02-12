package com.amoon.recipelist.data.repository.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.amoon.recipelist.data.ApiService
import com.amoon.recipelist.data.model.Drink
import io.reactivex.disposables.CompositeDisposable

// Data source factory for the data source
class RecipeDataSourceFactory(
    private val keyword: String,
    private val apiService: ApiService,
    private val disposables: CompositeDisposable
) : DataSource.Factory<String, Drink>() {

    val sourceLiveData = MutableLiveData<RecipeDataSource>()

    override fun create(): DataSource<String, Drink> {
        val source = RecipeDataSource(keyword, apiService, disposables)
        sourceLiveData.postValue(source)
        return source
    }
}