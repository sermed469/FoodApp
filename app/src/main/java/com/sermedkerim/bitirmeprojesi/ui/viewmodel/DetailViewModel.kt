package com.sermedkerim.bitirmeprojesi.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.sermedkerim.bitirmeprojesi.data.repo.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var foodRepository: FoodRepository) : ViewModel(){
}