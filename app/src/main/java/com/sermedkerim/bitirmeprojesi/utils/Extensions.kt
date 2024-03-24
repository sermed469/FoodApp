package com.sermedkerim.bitirmeprojesi.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide

fun Navigation.navigate(it: View, id:Int){
    Navigation.findNavController(it).navigate(id)
}

fun Navigation.navigate(navHostFragment: NavHostFragment, id:Int){
    navHostFragment.findNavController().navigate(id)
}

fun Navigation.navigate(it: View,action: NavDirections){
    Navigation.findNavController(it).navigate(action)
}

