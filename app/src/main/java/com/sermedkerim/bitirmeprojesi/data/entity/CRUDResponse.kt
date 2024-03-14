package com.sermedkerim.bitirmeprojesi.data.entity

import com.google.gson.annotations.SerializedName

data class CRUDResponse(@SerializedName(value = "success") var success:Int,
                        @SerializedName(value = "message") var message:String) {
}