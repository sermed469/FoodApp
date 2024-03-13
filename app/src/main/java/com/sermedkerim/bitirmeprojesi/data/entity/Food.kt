package com.sermedkerim.bitirmeprojesi.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serial
import java.io.Serializable

data class Food(@SerializedName(value = "yemek_id") var id:Int,
                @SerializedName(value = "yemek_adi") var name:String,
                @SerializedName(value = "yemek_resim_adi") var imageName:String,
                @SerializedName(value = "yemek_fiyat") var price:Int) : Serializable{
}