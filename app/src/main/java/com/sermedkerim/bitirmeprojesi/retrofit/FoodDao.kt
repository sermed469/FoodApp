package com.sermedkerim.bitirmeprojesi.retrofit

import com.sermedkerim.bitirmeprojesi.data.entity.CRUDResponse
import com.sermedkerim.bitirmeprojesi.data.entity.FoodResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodDao {
    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getAllFoods():FoodResponse
    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun addFoodToCard(@Field("yemek_adi") foodName:String,
                              @Field("yemek_resim_adi") foodImageName:String,
                              @Field("yemek_fiyat") foodPrice:Int,
                              @Field("yemek_siparis_adet") numberOfFoods:Int,
                              @Field("kullanici_adi") username:String):CRUDResponse
}