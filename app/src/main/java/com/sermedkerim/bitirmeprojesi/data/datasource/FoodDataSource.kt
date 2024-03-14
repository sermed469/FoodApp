package com.sermedkerim.bitirmeprojesi.data.datasource

import com.sermedkerim.bitirmeprojesi.data.entity.Food
import com.sermedkerim.bitirmeprojesi.retrofit.FoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDataSource(var foodDao: FoodDao) {
    suspend fun getAllFoods():List<Food> = withContext(Dispatchers.IO){
        return@withContext foodDao.getAllFoods().foodList
    }

    suspend fun addFoodToCart(food:Food,numberOfFoods:Int) = withContext(Dispatchers.IO){
        val username = "sermed_kerim"
        return@withContext foodDao.addFoodToCard(food.name,food.imageName,food.price,numberOfFoods,username)
    }
}