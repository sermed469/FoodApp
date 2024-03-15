package com.sermedkerim.bitirmeprojesi.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CartFood(@SerializedName("sepet_yemek_id") var id:Int,
                    @SerializedName("yemek_adi") var name:String,
                    @SerializedName("yemek_resim_adi") var imgName:String,
                    @SerializedName("yemek_fiyat") var price:Int,
                    @SerializedName("yemek_siparis_adet") var number:Int,
                    @SerializedName("ykullanici_adi") var username:String){
}