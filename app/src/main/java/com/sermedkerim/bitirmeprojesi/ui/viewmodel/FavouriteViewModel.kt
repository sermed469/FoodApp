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
class FavouriteViewModel @Inject constructor(var foodRepository: FoodRepository) : ViewModel() {
    var favouriteFoodList = MutableLiveData<List<Food>>()

    init {
        getFavouriteFoods()
    }

    fun getFavouriteFoods(){
        CoroutineScope(Dispatchers.Main).launch {
            favouriteFoodList.value = foodRepository.getFavouriteFoods()
        }
    }

    fun deleteFavouriteFood(foodName: String){
        CoroutineScope(Dispatchers.Main).launch {
            foodRepository.deleteFavouriteFood(foodName)
            getFavouriteFoods()
        }
    }
}