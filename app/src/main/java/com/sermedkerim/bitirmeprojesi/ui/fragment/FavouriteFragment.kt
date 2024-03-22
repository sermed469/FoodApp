package com.sermedkerim.bitirmeprojesi.ui.fragment

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sermedkerim.bitirmeprojesi.R
import com.sermedkerim.bitirmeprojesi.databinding.FragmentFavouriteBinding
import com.sermedkerim.bitirmeprojesi.databinding.NofavouriteFoodBinding
import com.sermedkerim.bitirmeprojesi.databinding.NofoodCartBinding
import com.sermedkerim.bitirmeprojesi.ui.adapter.FavouriteAdapter
import com.sermedkerim.bitirmeprojesi.ui.adapter.FoodAdapter
import com.sermedkerim.bitirmeprojesi.ui.viewmodel.FavouriteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouriteFragment : Fragment() {
    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var binding_nofav: NofavouriteFoodBinding
    private lateinit var viewModel:FavouriteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(inflater,container,false)
        binding_nofav = NofavouriteFoodBinding.inflate(inflater,container,false)

        binding.recyclerViewFavouriteFoods.layoutManager = LinearLayoutManager(requireContext())

        viewModel.favouriteFoodList.observe(viewLifecycleOwner){
            if(it.isEmpty()){
                binding.recyclerViewFavouriteFoods.visibility = View.GONE

                if(binding_nofav.root.parent != null){
                    val noFoodLayout = binding_nofav.root.parent as ViewGroup
                    noFoodLayout.removeView(binding_nofav.root)
                }
                binding_nofav.root.gravity = Gravity.CENTER
                binding.root.addView(binding_nofav.root)
            }else{
                val adapter = FavouriteAdapter(it,viewModel)
                binding.recyclerViewFavouriteFoods.adapter = adapter
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp : FavouriteViewModel by viewModels()
        viewModel = temp
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavouriteFoods()
    }
}