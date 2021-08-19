package com.justclack.adschnager.pojos

import com.google.gson.annotations.SerializedName

open class RealAdsPoJo(
    @SerializedName("ad_serving_limited") val ad_serving_limited: Boolean = false,
    @SerializedName("list") val adList: ArrayList<AdList> = ArrayList()
)