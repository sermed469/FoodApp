package com.sermedkerim.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.sermedkerim.bitirmeprojesi.data.entity.Food
import com.sermedkerim.bitirmeprojesi.data.repo.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var foodRepository: FoodRepository) : ViewModel(){
    fun addFoodToCart(food: Food, numberOfFoods:Int){
        CoroutineScope(Dispatchers.Main).launch {
            foodRepository.addFoodToCart(food,numberOfFoods)
        }
    }
}