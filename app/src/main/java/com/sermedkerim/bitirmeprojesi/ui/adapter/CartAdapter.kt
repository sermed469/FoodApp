package com.sermedkerim.bitirmeprojesi.ui.adapter

import android.app.FragmentManager
import android.app.FragmentTransaction
import android.content.res.Resources
import android.content.res.Resources.Theme
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.sermedkerim.bitirmeprojesi.MainActivity
import com.sermedkerim.bitirmeprojesi.R
import com.sermedkerim.bitirmeprojesi.data.entity.CartFood
import com.sermedkerim.bitirmeprojesi.databinding.CartCardItemBinding
import com.sermedkerim.bitirmeprojesi.ui.fragment.CustomDialogFragment
import com.sermedkerim.bitirmeprojesi.ui.viewmodel.CartViewModel
import dagger.hilt.android.internal.managers.FragmentComponentManager

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

//            val activity = FragmentComponentManager.findActivity(binding.root.context) as MainActivity
//            showDialog(activity.fragmentManager)

            MaterialAlertDialogBuilder(binding.root.context,com.google.android.material.R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog)
                .setMessage("Sepetten silmek istediğinize emin misiniz?")
                .setNegativeButton("Hayır"){ dialog,which ->

                }
                .setPositiveButton("Evet"){ dialog,which ->
                    viewModel.deleteCartFood(cartFood.id,cartFood.name)
                    Snackbar.make(it,"Yemek sepetten silindi",Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(it.context.getColor(R.color.colorSecondary))
                        .setTextColor(Color.WHITE)
                        .show()
                }
                .show()
        }
    }

    override fun getItemCount(): Int {
        return cartFoodList.size
    }

    fun showDialog(supportFragmentManager:FragmentManager) {
        val fragmentManager = supportFragmentManager

        val newFragment = CustomDialogFragment()
        if (false) {
            // The device is using a large layout, so show the fragment as a
            // dialog.
            newFragment.show(fragmentManager, "dialog")
        } else {
            // The device is smaller, so show the fragment fullscreen.
            val transaction = fragmentManager.beginTransaction()
            // For a polished look, specify a transition animation.
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            // To make it fullscreen, use the 'content' root view as the container
            // for the fragment, which is always the root view for the activity.
            transaction
                .add(android.R.id.content, newFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}