package com.jetbrains.greeting.shared

import io.ktor.client.*

internal interface HttpClientApi {

    fun getHttpClient(
        httpEngineProvider: HttpEngineProvider? = null,
        baseUrl: String,
        shouldEnableLogging: Boolean = false
    ): HttpClient

}