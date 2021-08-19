package com.justclack.adschnager.pojos
import com.google.gson.annotations.SerializedName

data class Data (

	@SerializedName("type") val type : String,
	@SerializedName("app_id") val app_id : String,
	@SerializedName("banner_id") val banner_id : String,
	@SerializedName("interstitial_id") val interstitial_id : String
)