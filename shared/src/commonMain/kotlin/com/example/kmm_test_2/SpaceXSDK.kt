package com.example.kmm_test_2

import cache.DataBase
import cache.DatabaseDriverFactory
import com.example.kmm_test_2.entity.RocketLaunch
import network.SpaceXApi

class SpaceXSDK(databaseDriverFactory: DatabaseDriverFactory) {

    private val database = DataBase(databaseDriverFactory)
    private val api = SpaceXApi()


    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearDatabase()
                database.createLaunches(it)
            }
        }
    }
}