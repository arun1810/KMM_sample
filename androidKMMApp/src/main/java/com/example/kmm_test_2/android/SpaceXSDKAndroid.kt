package com.example.kmm_test_2.android

import android.content.Context
import cache.DatabaseDriverFactory
import com.example.kmm_test_2.SpaceXSDK
import com.example.kmm_test_2.entity.RocketLaunch

class SpaceXSDKAndroid(private val context: Context) {

    suspend fun getAllLaunches(forceReload:Boolean):List<RocketLaunch>{
        val sdk  = SpaceXSDK(DatabaseDriverFactory(context))
        return sdk.getLaunches(forceReload)
    }
}