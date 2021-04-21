package com.justclack.adschnager

interface OnSuccessResponse <T>{
    fun changeAppId(rawResponse: T)
}