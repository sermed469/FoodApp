package com.sermedkerim.bitirmeprojesi.data.datasource

import android.util.Log
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

    suspend fun addFoodToCart(food:Food,numberOfFoods:Int) {
        try {
            foodDao.addFoodToCard(food.name,food.imageName,food.price,numberOfFoods,"sermed_kerim")
        }catch (e:Exception){

        }
    }

    suspend fun getCartFoods():List<CartFood> = withContext(Dispatchers.IO){
        var cartFoodList = listOf<CartFood>()
        var foodList = listOf<Food>()
        try {
            cartFoodList = foodDao.getCartFoods("sermed_kerim").cartFoods
            foodList = foodDao.getAllFoods().foodList
        }catch (e:Exception){
            return@withContext listOf<CartFood>()
        }

        var newCartFoodList = ArrayList<CartFood>()
        var numberOfFood = 0
        for(food in foodList){
            numberOfFood = 0
            for(cartFood in cartFoodList){
                if(food.name == cartFood.name){
                    numberOfFood += cartFood.number
                }
            }
            if(numberOfFood != 0){
                newCartFoodList.add(CartFood(0,food.name,food.imageName,food.price,numberOfFood,"sermed_kerim"))
            }
        }

        return@withContext newCartFoodList
    }

    suspend fun deleteCartFood(id:Int,name:String){
        var cartFoodList = listOf<CartFood>()
        cartFoodList = foodDao.getCartFoods("sermed_kerim").cartFoods

        for(cartFood in cartFoodList){
            if(cartFood.name == name){
                try {
                    foodDao.deleteCartFood(cartFood.id,"sermed_kerim")
                }catch (e:Exception){

                }
            }
        }
    }
}