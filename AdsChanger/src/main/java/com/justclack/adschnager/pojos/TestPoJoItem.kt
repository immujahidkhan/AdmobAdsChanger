package com.justclack.adschnager.pojos


import com.google.gson.annotations.SerializedName

data class TestPoJoItem(
    @SerializedName("app_id")
    val appId: String,
    @SerializedName("app_open")
    val appOpen: String,
    @SerializedName("banner_id")
    val bannerId: String,
    @SerializedName("interstitial_id")
    val interstitialId: String,
    @SerializedName("package")
    val packageX: String
)