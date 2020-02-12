package com.amoon.recipelist.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.amoon.recipelist.data.model.Drink
import com.amoon.recipelist.data.repository.AppRepository
import com.amoon.recipelist.data.repository.Listing
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: AppRepository,
    private val disposables: CompositeDisposable
) : ViewModel() {

    private val pageResult = MutableLiveData<Listing<Drink>>()
    private val query = PublishSubject.create<String>()

    val pages = switchMap(pageResult) { it.pages }
    val networkState = switchMap(pageResult) { it.networkState }


    init {
        query
            .debounce(1000, TimeUnit.MILLISECONDS)
            .filter { it.isNotEmpty() }
            .flatMapSingle { repository.getRecipeList(it) }
            .subscribe(pageResult::postValue)
            .addTo(disposables)
    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }


    fun listRecipe(keyword: String) {
        query.onNext(keyword)
    }


}