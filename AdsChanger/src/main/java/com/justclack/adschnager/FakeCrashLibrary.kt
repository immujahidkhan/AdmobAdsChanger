package com.justclack.adschnager

class FakeCrashLibrary private constructor() {
    companion object {
        private const val TAG = "FakeCrashLibrary"
        private val timberLog = TimberLog(TAG)
        fun log(priority: Int, tag: String?, message: String?) {
            //timberLog.e("$message : $priority : $tag")
        }

        fun logWarning(t: Throwable?) {
            timberLog.e("" + t)
        }

        fun logError(t: Throwable?) {
            timberLog.e("" + t)
        }
    }

    init {
        throw AssertionError("No instances.")
    }
}