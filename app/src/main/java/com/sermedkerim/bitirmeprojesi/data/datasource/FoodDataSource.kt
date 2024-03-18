package com.sermedkerim.bitirmeprojesi.data.datasource

import android.util.Log
import com.sermedkerim.bitirmeprojesi.AppPref
import com.sermedkerim.bitirmeprojesi.data.entity.CartFood
import com.sermedkerim.bitirmeprojesi.data.entity.Food
import com.sermedkerim.bitirmeprojesi.retrofit.FoodDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.http.Field

class FoodDataSource(var foodDao: FoodDao,var appPref: AppPref) {
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

    suspend fun getFavouriteFoods():List<Food> =
        withContext(Dispatchers.IO){
            val allFoods = foodDao.getAllFoods().foodList
            val favouriteFoods = appPref.getFavourite()
            val favouriteFoodList = ArrayList<Food>()
            if(favouriteFoods != null){
                for(f in favouriteFoods){
                    favouriteFoodList.add(allFoods.find { food: Food -> food.name == f }!!)
                    Log.e("Fav",f)
                }
            }else{
            }
            return@withContext favouriteFoodList
    }

    suspend fun addFavouriteFood(foodName: String) =
        withContext(Dispatchers.IO){
            val favouriteFoods = appPref.getFavourite()
            if(favouriteFoods != null){
                favouriteFoods.plus(foodName)
                appPref.saveFavourite(favouriteFoods.plus(foodName))
            }else{
                val newFavouriteFoods = HashSet<String>()
                newFavouriteFoods.add(foodName)
                appPref.saveFavourite(newFavouriteFoods)
            }
    }

    suspend fun deleteFavouriteFood(foodName: String) =
        withContext(Dispatchers.IO){
            val favouriteFoods = appPref.getFavourite()
            if(favouriteFoods != null){
                favouriteFoods.minus(foodName)
                appPref.deleteFavourite()
                appPref.saveFavourite(favouriteFoods.minus(foodName))
            }else{
            }
        }
    }