package com.sermedkerim.bitirmeprojesi.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.sermedkerim.bitirmeprojesi.R
import com.sermedkerim.bitirmeprojesi.SharedViewModel
import com.sermedkerim.bitirmeprojesi.data.entity.CartFood
import com.sermedkerim.bitirmeprojesi.databinding.CartCardItemBinding
import com.sermedkerim.bitirmeprojesi.ui.viewmodel.CartViewModel

class CartAdapter(var cartFoodList: List<CartFood>,var viewModel:CartViewModel,var fragmentActivity: FragmentActivity) : RecyclerView.Adapter<CartAdapter.CartFoodViewHolder>() {
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
            MaterialAlertDialogBuilder(binding.root.context,com.google.android.material.R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setMessage("Sepetten silmek istediğinize emin misiniz?")
                .setNegativeButton("Hayır"){ dialog,which ->

                }
                .setPositiveButton("Evet"){ dialog,which ->
                    viewModel.deleteCartFood(cartFood.id,cartFood.name)

                    //mainActivity.getMainActivityViewModel().getNumberOfFoods()

                    Snackbar.make(it,"Yemek sepetten silindi",Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(it.context.getColor(R.color.colorSecondary))
                        .setTextColor(Color.WHITE)
                        .show()

                    val model = ViewModelProvider(fragmentActivity).get(SharedViewModel::class.java)
                    //model.getNumberOfFoods()
                    model.deleteFoodFromCart()
                }
                .show()
        }
    }

    override fun getItemCount(): Int {
        return cartFoodList.size
    }
}