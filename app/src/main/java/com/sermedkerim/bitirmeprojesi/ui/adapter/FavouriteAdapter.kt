package com.sermedkerim.bitirmeprojesi.ui.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sermedkerim.bitirmeprojesi.R
import com.sermedkerim.bitirmeprojesi.data.entity.Food
import com.sermedkerim.bitirmeprojesi.databinding.FoodCardItemBinding
import com.sermedkerim.bitirmeprojesi.ui.fragment.FavouriteFragmentDirections
import com.sermedkerim.bitirmeprojesi.ui.fragment.MainPageFragmentDirections
import com.sermedkerim.bitirmeprojesi.ui.viewmodel.FavouriteViewModel
import com.sermedkerim.bitirmeprojesi.ui.viewmodel.MainPageViewModel

class FavouriteAdapter(var foodList: List<Food>, var viewModel: FavouriteViewModel)  : RecyclerView.Adapter<FavouriteAdapter.FoodCardItemViewHolder>() {
    inner class FoodCardItemViewHolder(var binding: FoodCardItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCardItemViewHolder {
        val binding = FoodCardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FoodCardItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodCardItemViewHolder, position: Int) {
        val binding = holder.binding
        val food = foodList.get(position)

        binding.textViewFoodNameCardItem.text = food.name
        binding.textViewFoodPriceCardItem.text = "${food.price} TL"
        binding.toogleButttonAddFovourite.icon = ContextCompat.getDrawable(binding.root.context, R.drawable.like_symbol)

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.imageName}"
        Glide.with(binding.root.context).load(url).override(512,512).into(binding.imageViewFoodCardItem)

        binding.root.setOnClickListener {
            val action = FavouriteFragmentDirections.navigationFromFavouritesToDetail(food)
            it.findNavController().navigate(action)
        }

        binding.toogleButttonAddFovourite.setOnClickListener {
            viewModel.deleteFavouriteFood(food.name)

            Snackbar.make(it,"Yemek favorilerden silindi", Snackbar.LENGTH_SHORT)
                .setTextColor(Color.WHITE)
                .setBackgroundTint(it.context.getColor(R.color.colorSecondary))
                .show()
        }
}

    override fun getItemCount(): Int {
        return foodList.size
    }
}