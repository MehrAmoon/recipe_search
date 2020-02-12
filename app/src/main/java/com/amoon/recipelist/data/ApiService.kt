package com.amoon.recipelist.data

import com.amoon.recipelist.data.model.Drinks
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("filter.php?")
    fun getRecipeList(
        @Query("i") keyword: String
    ): Single<Drinks>?
}