package com.justclack.adschnager.pojos


import com.google.gson.annotations.SerializedName

data class RealAdItem(
    @SerializedName("app_id")
    var appId: String,
    @SerializedName("app_open")
    var appOpen: String,
    @SerializedName("banner_id")
    var bannerId: String,
    @SerializedName("interstitial_id")
    var interstitialId: String,
    @SerializedName("package")
    var packageX: String
)