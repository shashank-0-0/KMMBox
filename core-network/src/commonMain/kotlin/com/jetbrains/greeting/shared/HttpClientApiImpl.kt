package com.jetbrains.greeting.shared

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class HttpClientApiImpl : HttpClientApi {

    override fun getHttpClient(
        httpEngineProvider: HttpEngineProvider?,
        baseUrl: String,
        shouldEnableLogging: Boolean
    ): HttpClient {

        return HttpClient() {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                    useAlternativeNames = true
                    encodeDefaults = true
                    explicitNulls = false
                })
            }
            install(Logging) {
                level = LogLevel.ALL
            }
        }
    }

}