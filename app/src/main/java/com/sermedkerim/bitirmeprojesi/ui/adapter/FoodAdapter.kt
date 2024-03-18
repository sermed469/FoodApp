package com.sermedkerim.bitirmeprojesi.ui.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


class FoodAdapter(var foodList: List<Food>,var viewModel: MainPageViewModel)  : RecyclerView.Adapter<FoodAdapter.FoodCardItemViewHolder>() {
    inner class FoodCardItemViewHolder(var binding: FoodCardItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCardItemViewHolder {
        val binding = FoodCardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FoodCardItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodCardItemViewHolder, position: Int) {
        val binding = holder.binding
        val food = foodList.get(position)

        if(viewModel.favouriteFoods.value != null){
            if(viewModel.favouriteFoods.value!!.find { fvFood: Food ->  fvFood.name == food.name} != null){
                binding.toogleButttonAddFovourite.icon = ContextCompat.getDrawable(binding.root.context,R.drawable.like_symbol)
                binding.toogleButttonAddFovourite.isChecked = true
            }
        }

        binding.textViewFoodNameCardItem.text = food.name
        binding.textViewFoodPriceCardItem.text = "${food.price} TL"

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.imageName}"
        Glide.with(binding.root.context).load(url).override(512,512).into(binding.imageViewFoodCardItem)

        binding.root.setOnClickListener {
            val action = MainPageFragmentDirections.navigationFromMainPageToDetail(food)
            it.findNavController().navigate(action)
        }

        var message = "Yemek favorilere eklendi"
        var snackbarColor = R.color.appColor

        binding.toogleButttonAddFovourite.addOnCheckedChangeListener { materialButton, b ->

            if(b){
                materialButton.icon = ContextCompat.getDrawable(materialButton.context,R.drawable.like_symbol)
                message = "Yemek favorilere eklendi"
                snackbarColor = R.color.appColor

//                CoroutineScope(Dispatchers.Main).launch {
//                    val favouriteFoods = appPref.getFavourite()
//                    if(favouriteFoods != null){
//                        favouriteFoods.plus(food.name)
//                        appPref.saveFavourite(favouriteFoods.plus(food.name))
//                    }else{
//                        val newFavouriteFoods = HashSet<String>()
//                        newFavouriteFoods.add(food.name)
//                        appPref.saveFavourite(newFavouriteFoods)
//                    }
//                }

                viewModel.addFavouriteFood(food.name)
            }else{
                materialButton.icon = ContextCompat.getDrawable(materialButton.context,R.drawable.favourite)
                message = "Yemek favorilerden silindi"
                snackbarColor = R.color.colorSecondary

//                CoroutineScope(Dispatchers.Main).launch {
//                    val favouriteFoods = appPref.getFavourite()
//                    if(favouriteFoods != null){
//                        favouriteFoods.minus(food.name)
//                        appPref.deleteFavourite()
//                        appPref.saveFavourite(favouriteFoods.minus(food.name))
//                    }else{
//                    }
//                }

                viewModel.deleteFavouriteFood(food.name)
            }

            Snackbar.make(materialButton,message,Snackbar.LENGTH_SHORT)
                .setTextColor(Color.WHITE)
                .setBackgroundTint(materialButton.context.getColor(snackbarColor))
                .show()
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}