package com.sermedkerim.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    init {
        getAllFoods()
    }

    fun getAllFoods(){
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = foodRepository.getAllFoods()
        }
    }
}