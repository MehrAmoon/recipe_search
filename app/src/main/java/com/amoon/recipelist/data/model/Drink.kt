package com.amoon.recipelist.data.model

import com.google.gson.annotations.SerializedName


data class Drink(@SerializedName("strDrink") val strDrink :String,
                 @SerializedName("strDrinkThumb") val strDrinkThumb :String,
                 @SerializedName("idDrink") val idDrink :String)

