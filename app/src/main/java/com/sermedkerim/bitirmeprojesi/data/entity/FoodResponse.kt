package com.sermedkerim.bitirmeprojesi.data.entity

import com.google.gson.annotations.SerializedName

data class FoodResponse(@SerializedName(value = "yemekler") var foodList:List<Food>,
                        @SerializedName(value = "success") var success:Int) {
}