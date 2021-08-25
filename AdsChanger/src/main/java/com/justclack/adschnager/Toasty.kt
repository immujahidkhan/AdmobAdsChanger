package com.justclack.flowerwallpapers.utils

import android.content.Context
import com.sdsmdg.tastytoast.TastyToast

class Toasty(var context: Context) {
    fun showErrorToasty(msg: String) {
        TastyToast.makeText(context, "" + msg, TastyToast.LENGTH_SHORT, TastyToast.ERROR)
    }

    fun showSuccessToasty(msg: String) {
        TastyToast.makeText(context, "" + msg, TastyToast.LENGTH_SHORT, TastyToast.SUCCESS)
    }

    fun showInfoToasty(msg: String) {
        TastyToast.makeText(context, "" + msg, TastyToast.LENGTH_SHORT, TastyToast.INFO)
    }

    fun ShowWarningToasty(msg: String) {
        TastyToast.makeText(context, "" + msg, TastyToast.LENGTH_SHORT, TastyToast.WARNING)
    }

    fun showConfusingToast(msg: String) {
        TastyToast.makeText(context, "" + msg, TastyToast.LENGTH_SHORT, TastyToast.CONFUSING)
    }

    fun showDefaultToast(msg: String) {
        TastyToast.makeText(context, "" + msg, TastyToast.LENGTH_SHORT, TastyToast.DEFAULT)
    }
}