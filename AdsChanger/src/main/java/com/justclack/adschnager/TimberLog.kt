package com.justclack.flowerwallpapers.utils

import timber.log.Timber

class TimberLog(private val TAG: String) {
    fun d(s: String) {
        Timber.tag(TAG).d(s)
    }

    fun e(s: String) {
        Timber.tag(TAG).e(s)
    }

    fun i(s: String) {
        Timber.tag(TAG).i(s)
    }
}