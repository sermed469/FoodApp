package com.sermedkerim.bitirmeprojesi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sermedkerim.bitirmeprojesi.data.entity.Food
import com.sermedkerim.bitirmeprojesi.databinding.FoodCardItemBinding

class FoodAdapter(var foodList: List<Food>) : RecyclerView.Adapter<FoodAdapter.FoodCardItemViewHolder>() {
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

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.imageName}"
        Glide.with(binding.root.context).load(url).override(512,512).into(binding.imageViewFoodCardItem)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}