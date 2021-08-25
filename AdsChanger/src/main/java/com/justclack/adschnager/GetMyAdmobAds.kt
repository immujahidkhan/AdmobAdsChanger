package com.justclack.adschnager

import android.app.Activity
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import com.genericresponseretrofit.GenericResponseManager
import com.genericresponseretrofit.onGenericResponseListener
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.justclack.adschnager.pojos.RealAd
import com.justclack.adschnager.pojos.RealAdItem
import okhttp3.ResponseBody
import retrofit2.Response
import timber.log.Timber


class GetMyAdmobAds {
    companion object {
        fun getAds(
            activity: Activity,
            packageName: String,
            baseURL: String,
            endPoint: String,
            param: OnSuccessResponse<RealAdItem>
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
                            var resList = RealAd()
                            val gson = Gson()
                            val json: String = response.body().toString()
                            val type = object : TypeToken<RealAd>() {}.type
                            resList = gson.fromJson<RealAd>(json, type)
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
        fun loadBannerAds(adsPoJo: RealAdItem, context: Context, adContainer: LinearLayout) {
            val adView = AdView(context)
            adView.adSize = AdSize.BANNER
            adView.adUnitId = adsPoJo.bannerId
            val adRequest = AdRequest.Builder().build()
            adView.loadAd(adRequest)
            val params: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            adContainer.addView(adView, params)
        }

        fun Activity.LoadFullPageAd(adsPoJo: RealAdItem) {
            var mInterstitialAd: InterstitialAd? = null
            val adRequest = AdRequest.Builder().build()
            InterstitialAd.load(
                this,
                adsPoJo.interstitialId,
                adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        mInterstitialAd = null
                    }

                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        Timber.tag(TAG).d("Ad was loaded.")
                        mInterstitialAd = interstitialAd
                    }
                })
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Timber.tag(TAG).d("Ad was dismissed.")
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                    Timber.tag(TAG).d("Ad failed to show.")
                }

                override fun onAdShowedFullScreenContent() {
                    Timber.tag(TAG).d("Ad showed fullscreen content.")
                    mInterstitialAd = null;
                }
            }
            if (mInterstitialAd != null) {
                mInterstitialAd?.show(this)
            } else {
                Log.d("TAG", "The interstitial ad wasn't ready yet.")
            }
        }
    }
}