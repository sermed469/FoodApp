package com.sermedkerim.bitirmeprojesi.data.repo

import com.sermedkerim.bitirmeprojesi.data.datasource.FoodDataSource
import com.sermedkerim.bitirmeprojesi.data.entity.Food

class FoodRepository(var foodDataSource: FoodDataSource) {

    suspend fun getAllFoods():List<Food> = foodDataSource.getAllFoods()
}