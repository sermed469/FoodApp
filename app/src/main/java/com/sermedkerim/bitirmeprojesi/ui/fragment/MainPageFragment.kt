package com.sermedkerim.bitirmeprojesi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.sermedkerim.bitirmeprojesi.databinding.FragmentMainPageBinding
import com.sermedkerim.bitirmeprojesi.ui.adapter.FoodAdapter
import com.sermedkerim.bitirmeprojesi.ui.viewmodel.MainPageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainPageFragment : Fragment() {
    private lateinit var binding: FragmentMainPageBinding
    private lateinit var viewModel: MainPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(LayoutInflater.from(requireContext()),container,false)

        binding.recyclerViewAllFoodList.layoutManager = LinearLayoutManager(requireContext())

        viewModel.foodList.observe(viewLifecycleOwner){
            val foodAdapter = FoodAdapter(it)
            binding.recyclerViewAllFoodList.adapter = foodAdapter
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp : MainPageViewModel by viewModels()
        viewModel = temp
    }
}