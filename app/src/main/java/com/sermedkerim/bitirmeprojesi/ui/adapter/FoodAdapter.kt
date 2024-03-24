package com.sermedkerim.bitirmeprojesi.ui.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sermedkerim.bitirmeprojesi.AppPref
import com.sermedkerim.bitirmeprojesi.MainActivity
import com.sermedkerim.bitirmeprojesi.R
import com.sermedkerim.bitirmeprojesi.data.entity.Food
import com.sermedkerim.bitirmeprojesi.databinding.FoodCardItemBinding
import com.sermedkerim.bitirmeprojesi.ui.fragment.MainPageFragmentDirections
import com.sermedkerim.bitirmeprojesi.ui.viewmodel.MainPageViewModel
import com.sermedkerim.bitirmeprojesi.utils.navigate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


class FoodAdapter(var foodList: List<Food>,var favList: List<Food>?,var viewModel: MainPageViewModel,var viewLifecycleOwner: LifecycleOwner)  : RecyclerView.Adapter<FoodAdapter.FoodCardItemViewHolder>() {
    inner class FoodCardItemViewHolder(var binding: FoodCardItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCardItemViewHolder {
        val binding = FoodCardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FoodCardItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodCardItemViewHolder, position: Int) {
        val binding = holder.binding
        val food = foodList.get(position)

        if(position > foodList.size / 2){
            binding.toogleButttonAddFovourite.icon = ContextCompat.getDrawable(binding.root.context,R.drawable.favourite)
            binding.toogleButttonAddFovourite.isChecked = false
        }

        viewModel.favouriteFoods.observe(viewLifecycleOwner){
            if(it != null){
                if(it.find { fvFood: Food ->  fvFood.name == food.name} != null){
                    binding.toogleButttonAddFovourite.icon = ContextCompat.getDrawable(binding.root.context,R.drawable.like_symbol)
                    binding.toogleButttonAddFovourite.isChecked = true
                }
            }
        }

        binding.textViewFoodNameCardItem.text = food.name
        binding.textViewFoodPriceCardItem.text = "${food.price} TL"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.imageName}"
        Glide.with(binding.root.context).load(url).override(512,512).into(binding.imageViewFoodCardItem)

        binding.root.setOnClickListener {
            val action = MainPageFragmentDirections.navigationFromMainPageToDetail(food)
            Navigation.navigate(it,action)
        }

        binding.toogleButttonAddFovourite.addOnCheckedChangeListener { materialButton, b ->
            materialButton.setOnClickListener {
                if(b){
                    materialButton.icon = ContextCompat.getDrawable(materialButton.context,R.drawable.like_symbol)
                    viewModel.addFavouriteFood(food.name)
                }else{
                    materialButton.icon = ContextCompat.getDrawable(materialButton.context,R.drawable.favourite)
                    viewModel.deleteFavouriteFood(food.name)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}