package network

import com.example.kmm_test_2.entity.RocketLaunch
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * this class executes network requests and deserializes JSON responses inti entities from entities package.
 */
class SpaceXApi {

    private val httpClient = HttpClient{
        install(ContentNegotiation){//this deserializes the GET request
            json(Json{
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun getAllLaunches():List<RocketLaunch>{ //this function is suspend because the get() function is suspend.
        return httpClient.get("https://api.spacexdata.com/v5/launches").body() // the http request is executed in the http client's thread pool.
    }
}