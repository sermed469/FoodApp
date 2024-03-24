package com.sermedkerim.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
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
    var favouriteFoods = MutableLiveData<List<Food>>()
    var numberOfFoods = MutableLiveData<Int>()

    init {
        getFavouriteFoods()
        numberOfFoods.value = 1
    }

    fun increaseNumberOfFood(){
        numberOfFoods.value = numberOfFoods.value?.plus(1)
    }

    fun substractNumberOfFood(){
        if(numberOfFoods.value != 1){
            numberOfFoods.value = numberOfFoods.value?.minus(1)
        }
    }

    fun addFoodToCart(food: Food, numberOfFoods:Int){
        CoroutineScope(Dispatchers.Main).launch {
            foodRepository.addFoodToCart(food,numberOfFoods)
        }
    }

    fun addFavouriteFood(foodName:String){
        CoroutineScope(Dispatchers.Main).launch {
            foodRepository.addFavouriteFood(foodName)
        }
    }

    fun deleteFavouriteFood(foodName: String){
        CoroutineScope(Dispatchers.Main).launch {
            foodRepository.deleteFavouriteFood(foodName)
        }
    }

    fun getFavouriteFoods(){
        CoroutineScope(Dispatchers.Main).launch {
            favouriteFoods.value = foodRepository.getFavouriteFoods()
        }
    }
}