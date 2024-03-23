package com.sermedkerim.bitirmeprojesi

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sermedkerim.bitirmeprojesi.data.repo.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(var foodRepository: FoodRepository) : ViewModel() {
    var numberofFoods = MutableLiveData<Int>()

    init {
        getNumberOfFoods()
    }

    fun getNumberOfFoods() {
        CoroutineScope(Dispatchers.Main).launch {
            numberofFoods.value = foodRepository.getCartFoods().size
        }
    }

    fun deleteFoodFromCart() {
        if(numberofFoods.value != null){
            numberofFoods.value = numberofFoods.value!! - 1
        }
    }
}