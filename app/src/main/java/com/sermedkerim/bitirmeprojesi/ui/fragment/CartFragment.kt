package com.sermedkerim.bitirmeprojesi.ui.fragment

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sermedkerim.bitirmeprojesi.databinding.FragmentCartBinding
import com.sermedkerim.bitirmeprojesi.databinding.NofoodCartBinding
import com.sermedkerim.bitirmeprojesi.ui.adapter.CartAdapter
import com.sermedkerim.bitirmeprojesi.ui.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var binding_nofood: NofoodCartBinding
    private lateinit var viewModel:CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(LayoutInflater.from(requireContext()),container,false)
        binding_nofood = NofoodCartBinding.inflate(LayoutInflater.from(requireContext()),container,false)

        binding.recyclerViewCart.layoutManager = LinearLayoutManager(requireContext())

        viewModel.cardFoods.observe(viewLifecycleOwner){
            if(it.isEmpty()){
                binding.recyclerViewCart.visibility = View.GONE
                binding.textViewTotalPrice.visibility = View.GONE
                binding.buttonCartPage.visibility = View.GONE
                binding.textViewToplamFiyat.visibility = View.GONE

                if(binding_nofood.root.parent != null){
                    val noFoodLayout = binding_nofood.root.parent as ViewGroup
                    noFoodLayout.removeView(binding_nofood.root)
                }
                binding_nofood.root.gravity = Gravity.CENTER
                binding.root.addView(binding_nofood.root)

            }else{
                binding.textViewTotalPrice.visibility = View.VISIBLE
                binding.buttonCartPage.visibility = View.VISIBLE
                binding.textViewToplamFiyat.visibility = View.VISIBLE

                val cartAdapter = CartAdapter(it,viewModel,requireActivity())
                binding.recyclerViewCart.adapter = cartAdapter

                viewModel.totalPrice.observe(viewLifecycleOwner){
                    binding.textViewTotalPrice.text = "${it.toString()} ₺"
                }

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