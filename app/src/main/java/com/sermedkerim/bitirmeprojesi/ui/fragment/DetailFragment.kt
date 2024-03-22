package com.sermedkerim.bitirmeprojesi.ui.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.sermedkerim.bitirmeprojesi.MainActivity
import com.sermedkerim.bitirmeprojesi.SharedViewModel
import com.sermedkerim.bitirmeprojesi.databinding.FragmentDetailBinding
import com.sermedkerim.bitirmeprojesi.ui.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(LayoutInflater.from(requireContext()),container,false)

        val args : DetailFragmentArgs by navArgs()
        val food = args.food

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${food.imageName}"
        Glide.with(binding.root.context).load(url).override(512,512).into(binding.imageViewFoodDetail)

        binding.textViewFoodNameDetail.text = food.name
        binding.textViewFoodPriceDetail.text = "${food.price} TL"

        binding.buttonAddFoodToCart.setOnClickListener {
            viewModel.addFoodToCart(food,binding.autoCompleteTextViewDetail.text.toString().toInt())
            it.findNavController().navigateUp()

            val activity = activity as MainActivity
            //activity.getMainActivityViewModel().getNumberOfFoods()

            val model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
            model.getNumberOfFoods()
            //model.addFoodToCart()

            Snackbar.make(it,"Yemek sepete eklendi",Snackbar.LENGTH_SHORT)
                .setTextColor(Color.WHITE)
                .setBackgroundTint(Color.parseColor("#fec429"))
                .show()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp : DetailViewModel by viewModels()
        viewModel = temp
    }
}