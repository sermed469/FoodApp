package com.sermedkerim.bitirmeprojesi.ui.fragment

import android.R
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
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
        binding.textViewFoodPriceDetail.text = "${food.price.toString()} TL"

        val numberOfFoods = ArrayList<String>()
        numberOfFoods.add("1")
        numberOfFoods.add("2")
        numberOfFoods.add("3")
        numberOfFoods.add("4")
        numberOfFoods.add("5")
        numberOfFoods.add("6")
        numberOfFoods.add("7")
        numberOfFoods.add("8")
        numberOfFoods.add("9")

        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.simple_list_item_1,numberOfFoods)
        binding.autoCompleteTextViewDetail.setAdapter(arrayAdapter)

        binding.buttonAddFoodToCart.setOnClickListener {
            Log.e("Sepete Ekle",binding.autoCompleteTextViewDetail.text.toString())
            //viewModel.addFoodToCart(food,binding.autoCompleteTextViewDetail.text.toString().toInt())
            it.findNavController().navigateUp()
            Snackbar.make(it,"Yemek sepete eklendi",Snackbar.LENGTH_SHORT)
                .setTextColor(Color.WHITE)
                .setBackgroundTint(Color.GREEN)
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