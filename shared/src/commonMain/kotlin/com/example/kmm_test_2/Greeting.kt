package com.example.kmm_test_2

import RocketLaunch
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class Greeting {
    private val platform: Platform = getPlatform()

    private val httpClient = HttpClient{
        install(ContentNegotiation){ // to deserialize the GET request the ContentNegotiation and Json pluglin are used
            json(Json{
                prettyPrint = true
                isLenient  = true
                ignoreUnknownKeys = true
            })
        }
    }
   suspend fun greet(): String {

        val rockets:List<RocketLaunch> = httpClient.get("https://api.spacexdata.com/v4/launches").body()
        val lastSuccessLaunch = rockets.last{it.launchSuccess == true}

        return "Hello, ${platform.name}! From Custom Greeting" +
                "\n\nThere are only ${daysUntilNewYear()} days left until New year🥳🎆" +
                "\n\nThe last successful launch was ${lastSuccessLaunch.launchDateUTC}‍🚀"+
                "\n\n The UUID is:${getUUID()}"
    }
}