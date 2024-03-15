package com.sermedkerim.bitirmeprojesi.ui.adapter

import android.content.res.Resources
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.sermedkerim.bitirmeprojesi.data.entity.CartFood
import com.sermedkerim.bitirmeprojesi.databinding.CartCardItemBinding
import com.sermedkerim.bitirmeprojesi.ui.viewmodel.CartViewModel

class CartAdapter(var cartFoodList: List<CartFood>,var viewModel:CartViewModel) : RecyclerView.Adapter<CartAdapter.CartFoodViewHolder>() {
    inner class CartFoodViewHolder(var binding: CartCardItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartFoodViewHolder {
        val binding = CartCardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartFoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartFoodViewHolder, position: Int) {
        val binding = holder.binding
        val cartFood = cartFoodList.get(position)

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${cartFood.imgName}"
        Glide.with(binding.root.context).load(url).override(512,512).into(binding.imageViewCartFoodCardItem)

        binding.textViewCardFoodNameCardItem.text = cartFood.name
        binding.textViewCartFoodPriceCardItem.text = "${cartFood.price.toString()} TL"
        binding.textViewNumberOfFoodsCartCardItem.text = "Adet: ${cartFood.number.toString()}"

        binding.imageViewDeleteCartFoodCardItem.setOnClickListener {

            MaterialAlertDialogBuilder(binding.root.context)
                .setMessage("Sepetten silmek istediğinize emin misiniz?")
                .setNegativeButton("Hayır"){ dialog,which ->

                }
                .setPositiveButton("Evet"){ dialog,which ->
                    viewModel.deleteCartFood(cartFood.id,cartFood.name)
                    Snackbar.make(it,"Yemek sepetten silindi",Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(Color.parseColor("#fec429"))
                        .setTextColor(Color.WHITE)
                        .show()
                }
                .show()
        }
    }

    override fun getItemCount(): Int {
        return cartFoodList.size
    }
}