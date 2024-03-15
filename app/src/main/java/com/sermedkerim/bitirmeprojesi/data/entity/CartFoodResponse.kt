package com.sermedkerim.bitirmeprojesi.data.entity

import com.google.gson.annotations.SerializedName

data class CartFoodResponse(@SerializedName("sepet_yemekler") var cartFoods:List<CartFood>,
                            @SerializedName("success") var success:Int) {
}