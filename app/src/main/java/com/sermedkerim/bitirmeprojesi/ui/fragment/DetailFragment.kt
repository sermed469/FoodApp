package com.sermedkerim.bitirmeprojesi.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.snackbar.Snackbar
import com.sermedkerim.bitirmeprojesi.R
import com.sermedkerim.bitirmeprojesi.SharedViewModel
import com.sermedkerim.bitirmeprojesi.data.entity.Food
import com.sermedkerim.bitirmeprojesi.databinding.FragmentDetailBinding
import com.sermedkerim.bitirmeprojesi.ui.viewmodel.DetailViewModel
import com.sermedkerim.bitirmeprojesi.utils.navigate
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    @androidx.annotation.OptIn(com.google.android.material.badge.ExperimentalBadgeUtils::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(LayoutInflater.from(requireContext()),container,false)

        val args : DetailFragmentArgs by navArgs()
        val food = args.food

        viewModel.favouriteFoods.observe(viewLifecycleOwner){
            if(it != null){
                if(it.find { fvFood: Food ->  fvFood.name == food.name} != null){
                    binding.toogleButttonFavDetail.icon = ContextCompat.getDrawable(binding.root.context,R.drawable.like_symbol)
                    binding.toogleButttonFavDetail.isChecked = true
                }
            }
        }

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.imageName}"
        Glide.with(binding.root.context).load(url).override(512,512).into(binding.imageViewFoodDetail)

        binding.textViewFoodNameDetail.text = food.name
        binding.textViewFoodPriceDetail.text = "${food.price} TL"

        viewModel.numberOfFoods.observe(viewLifecycleOwner){
            binding.textViewNumberOfFoodDetailPage.text = it.toString()
        }

        binding.imageViewAddFood.setOnClickListener {
            viewModel.increaseNumberOfFood()
        }

        binding.imageViewSubFood.setOnClickListener {
            viewModel.substractNumberOfFood()
        }

        val model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        binding.fabAddFoodToCart.setOnClickListener {
            viewModel.addFoodToCart(food,binding.textViewNumberOfFoodDetailPage.text.toString().toInt())
            it.findNavController().navigateUp()

            model.getNumberOfFoods()
            //model.addFoodToCart()

            Snackbar.make(it,"Yemek sepete eklendi",Snackbar.LENGTH_SHORT)
                .setTextColor(Color.WHITE)
                .setBackgroundTint(Color.parseColor("#fec429"))
                .show()
        }

        binding.fabGoToCart.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val badgeDrawable = BadgeDrawable.create(requireContext())
                    if(model.numberofFoods.value != null){
                        if(model.numberofFoods.value != 0){
                            badgeDrawable.number = model.numberofFoods.value!!
                            badgeDrawable.horizontalOffset = 32
                            badgeDrawable.verticalOffset = 32
                            badgeDrawable.backgroundColor = resources.getColor(R.color.colorSecondary)
                            BadgeUtils.attachBadgeDrawable(badgeDrawable,binding.fabGoToCart,null)
                            binding.fabGoToCart.getViewTreeObserver().removeOnGlobalLayoutListener(this)
                        }
                }
            }
        });

        binding.toogleButttonFavDetail.addOnCheckedChangeListener { materialButton, b ->
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

        binding.fabGoToCart.setOnClickListener {
            Navigation.navigate(it, R.id.navigationFromDetailPageToCartPage)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp : DetailViewModel by viewModels()
        viewModel = temp
    }
}