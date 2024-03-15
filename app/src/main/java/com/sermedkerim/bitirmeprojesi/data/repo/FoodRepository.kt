package com.sermedkerim.bitirmeprojesi.data.repo

import com.sermedkerim.bitirmeprojesi.data.datasource.FoodDataSource
import com.sermedkerim.bitirmeprojesi.data.entity.CartFood
import com.sermedkerim.bitirmeprojesi.data.entity.Food

class FoodRepository(var foodDataSource: FoodDataSource) {

    suspend fun getAllFoods():List<Food> = foodDataSource.getAllFoods()
    suspend fun addFoodToCart(food: Food,numberOfFoods:Int) = foodDataSource.addFoodToCart(food, numberOfFoods)
    suspend fun getCartFoods():List<CartFood> = foodDataSource.getCartFoods()
    suspend fun deleteCartFood(id:Int,name:String) = foodDataSource.deleteCartFood(id,name)
}