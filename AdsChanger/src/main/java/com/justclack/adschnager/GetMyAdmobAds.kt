package com.justclack.adschnager

import android.app.Activity
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import com.genericresponseretrofit.GenericResponseManager
import com.genericresponseretrofit.onGenericResponseListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.justclack.adschnager.pojos.TestPoJo
import com.justclack.adschnager.pojos.TestPoJoItem
import okhttp3.ResponseBody
import retrofit2.Response


class GetMyAdmobAds {
    companion object {
        fun getAds(
            activity: Activity,
            packageName: String,
            baseURL: String,
            endPoint: String,
            param: OnSuccessResponse<TestPoJoItem>
        ) {
            val mapString: MutableMap<String, String> = HashMap()
            GenericResponseManager(baseURL).getRequest(
                mapString,
                endPoint,
                object : onGenericResponseListener {
                    override fun onComplete() {

                    }

                    override fun onNext(response: Response<*>) {
                        if (response.isSuccessful) {
                            var resList = TestPoJo()
                            val gson = Gson()
                            val json: String = response.body().toString()
                            val type = object : TypeToken<TestPoJo>() {}.type
                            resList = gson.fromJson<TestPoJo>(json, type)
                            for (pojo in resList) {
                                if (pojo.packageX.equals(packageName, true)) {
                                    param.changeAppId(pojo)
                                    try {
                                        val ai: ApplicationInfo =
                                            activity.packageManager.getApplicationInfo(
                                                activity.packageName,
                                                PackageManager.GET_META_DATA
                                            )
                                        val bundle = ai.metaData
                                        val myApiKey =
                                            bundle.getString("com.google.android.gms.ads.APPLICATION_ID")
                                        Log.d(TAG, "Name Found: $myApiKey")
                                        ai.metaData.putString(
                                            "com.google.android.gms.ads.APPLICATION_ID",
                                            "ca-app-pub-3940256099942544~3347511713"
                                        )
                                        val ApiKey =
                                            bundle.getString("com.google.android.gms.ads.APPLICATION_ID")
                                        Log.d(TAG, "ReNamed Found: $ApiKey")
                                    } catch (e: PackageManager.NameNotFoundException) {
                                        Log.e(
                                            TAG,
                                            "Failed to load meta-data, NameNotFound: " + e.message
                                        )
                                    } catch (e: NullPointerException) {
                                        Log.e(
                                            TAG,
                                            "Failed to load meta-data, NullPointer: " + e.message
                                        )
                                    }
                                }
                            }
                        }
                    }

                    override fun onErrorBody(responseErrorBody: ResponseBody?) {
                        Log.d("Data", responseErrorBody.toString())
                    }
                })
        }

        private const val TAG = "GetMyAdmobAds"

    }
}