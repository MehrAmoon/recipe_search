package com.amoon.recipelist.data.repository

import com.amoon.recipelist.data.model.Drink
import io.reactivex.Single

interface AppRepository {

    fun getRecipeList(query: String): Single<Listing<Drink>>
}