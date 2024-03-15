package com.sermedkerim.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sermedkerim.bitirmeprojesi.data.entity.CartFood
import com.sermedkerim.bitirmeprojesi.data.repo.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var foodRepository: FoodRepository) : ViewModel(){
    var cardFoods = MutableLiveData<List<CartFood>>()
    init {
        getCartFoods()
    }
    fun getCartFoods(){
        CoroutineScope(Dispatchers.Main).launch {
            cardFoods.value = foodRepository.getCartFoods()
        }
    }

    fun deleteCartFood(id:Int,name:String){
        CoroutineScope(Dispatchers.Main).launch {
            foodRepository.deleteCartFood(id,name)
            getCartFoods()
        }
    }
}