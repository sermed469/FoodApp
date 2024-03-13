package com.sermedkerim.bitirmeprojesi.retrofit

class ApiUtils {
    companion object{
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getFoodDao():FoodDao{
            return RetrofitClient.getClient(baseUrl = BASE_URL).create(FoodDao::class.java)
        }
    }
}