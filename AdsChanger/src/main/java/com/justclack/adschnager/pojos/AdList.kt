package com.justclack.adschnager.pojos

import com.google.gson.annotations.SerializedName

data class AdList(

	@SerializedName("package") val packageName: String,
	@SerializedName("data") val data: List<Data>
)