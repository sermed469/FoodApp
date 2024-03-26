package com.sermedkerim.bitirmeprojesi.data.repo

import com.sermedkerim.bitirmeprojesi.AppPref
import com.sermedkerim.bitirmeprojesi.data.datasource.FoodDataSource
import com.sermedkerim.bitirmeprojesi.data.entity.CartFood
import com.sermedkerim.bitirmeprojesi.data.entity.Food

class FoodRepository(var foodDataSource: FoodDataSource) {

    suspend fun getAllFoods():List<Food> = foodDataSource.getAllFoods()
    suspend fun addFoodToCart(food: Food,numberOfFoods:Int) = foodDataSource.addFoodToCart(food, numberOfFoods)
    suspend fun getCartFoods():List<CartFood> = foodDataSource.getCartFoods()
    suspend fun deleteCartFood(id:Int,name:String) = foodDataSource.deleteCartFood(id,name)
    suspend fun addFavouriteFood(foodName: String) = foodDataSource.addFavouriteFood(foodName)
    suspend fun deleteFavouriteFood(foodName: String) = foodDataSource.deleteFavouriteFood(foodName)
    suspend fun getFavouriteFoods():List<Food> = foodDataSource.getFavouriteFoods()
    suspend fun search(searchString: String):List<Food> = foodDataSource.search(searchString)
    suspend fun getTotalPrice():Int = foodDataSource.getTotalPrice()
}