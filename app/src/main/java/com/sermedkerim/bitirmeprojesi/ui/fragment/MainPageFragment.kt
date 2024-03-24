package com.sermedkerim.bitirmeprojesi.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sermedkerim.bitirmeprojesi.AppPref
import com.sermedkerim.bitirmeprojesi.R
import com.sermedkerim.bitirmeprojesi.databinding.FragmentMainPageBinding
import com.sermedkerim.bitirmeprojesi.ui.adapter.FoodAdapter
import com.sermedkerim.bitirmeprojesi.ui.viewmodel.MainPageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

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
            val foodAdapter = FoodAdapter(it,viewModel.favouriteFoods.value,viewModel,viewLifecycleOwner)
            binding.recyclerViewAllFoodList.adapter = foodAdapter
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String): Boolean {
                viewModel.search(p0)
                return true
            }

            override fun onQueryTextSubmit(p0: String): Boolean {
                viewModel.search(p0)
                return true
            }
        })

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp : MainPageViewModel by viewModels()
        viewModel = temp
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavouriteFoods()
    }
}