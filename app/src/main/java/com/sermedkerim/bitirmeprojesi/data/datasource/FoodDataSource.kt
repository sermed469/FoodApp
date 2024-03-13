package com.sermedkerim.bitirmeprojesi.data.datasource

import com.sermedkerim.bitirmeprojesi.data.entity.Food
import com.sermedkerim.bitirmeprojesi.retrofit.FoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource(var foodDao: FoodDao) {

    suspend fun getAllFoods():List<Food> = withContext(Dispatchers.IO){
        return@withContext foodDao.getAllFoods().foodList
    }
}