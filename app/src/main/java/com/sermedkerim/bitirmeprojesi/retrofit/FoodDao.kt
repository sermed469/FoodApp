package com.sermedkerim.bitirmeprojesi.retrofit

import com.sermedkerim.bitirmeprojesi.data.entity.FoodResponse
import retrofit2.http.GET

interface FoodDao {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getAllFoods():FoodResponse
}