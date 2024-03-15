package com.sermedkerim.bitirmeprojesi.data.datasource

import com.sermedkerim.bitirmeprojesi.data.entity.CartFood
import com.sermedkerim.bitirmeprojesi.data.entity.Food
import com.sermedkerim.bitirmeprojesi.retrofit.FoodDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Field

class FoodDataSource(var foodDao: FoodDao) {
    suspend fun getAllFoods():List<Food> = withContext(Dispatchers.IO){
        return@withContext foodDao.getAllFoods().foodList
    }

    suspend fun addFoodToCart(food:Food,numberOfFoods:Int) = withContext(Dispatchers.IO){
        return@withContext foodDao.addFoodToCard(food.name,food.imageName,food.price,numberOfFoods,"sermed_kerim")
    }

    suspend fun getCartFoods():List<CartFood> = withContext(Dispatchers.IO){
        var cartFoodList = listOf<CartFood>()
        cartFoodList = foodDao.getCartFoods("sermed_kerim").cartFoods

        cartFoodList.map {

        }

        return@withContext foodDao.getCartFoods("sermed_kerim").cartFoods
    }

    suspend fun deleteCartFood(id:Int) = withContext(Dispatchers.IO){
        return@withContext foodDao.deleteCartFood(id,"sermed_kerim")
    }
}