package com.sermedkerim.bitirmeprojesi.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sermedkerim.bitirmeprojesi.databinding.FragmentCartBinding
import com.sermedkerim.bitirmeprojesi.ui.adapter.CartAdapter
import com.sermedkerim.bitirmeprojesi.ui.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel:CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(LayoutInflater.from(requireContext()),container,false)

        binding.recyclerViewCart.layoutManager = LinearLayoutManager(requireContext())

        viewModel.cardFoods.observe(viewLifecycleOwner){
            if(it.isEmpty()){
                binding.recyclerViewCart.visibility = View.GONE
                binding.textViewCartNoFoodInCart.visibility = View.VISIBLE
            }else{
                val cartAdapter = CartAdapter(it,viewModel)
                binding.recyclerViewCart.adapter = cartAdapter
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp : CartViewModel by viewModels()
        viewModel = temp
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCartFoods()
    }
}