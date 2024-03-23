package com.sermedkerim.bitirmeprojesi.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sermedkerim.bitirmeprojesi.AppPref
import com.sermedkerim.bitirmeprojesi.data.entity.Food
import com.sermedkerim.bitirmeprojesi.data.repo.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(var foodRepository: FoodRepository) : ViewModel() {
    var foodList = MutableLiveData<List<Food>>()
    var favouriteFoods = MutableLiveData<List<Food>>()

    init {
        getAllFoods()
        getFavouriteFoods()
    }

    fun getAllFoods(){
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = foodRepository.getAllFoods()
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

    fun search(searchString: String){
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = foodRepository.search(searchString)
        }
    }
}