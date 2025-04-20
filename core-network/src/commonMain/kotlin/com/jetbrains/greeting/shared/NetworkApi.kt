package com.jetbrains.greeting.shared

import io.ktor.client.HttpClient

class NetworkApi private constructor() : HttpClientApi by HttpClientApiImpl() {

    private var baseUrl: String? = null

    //Using this cuz im just biased towards this pattern

    class Builder {
        private var baseUrl: String? = null

        fun setBaseUrl(baseUrl: String) = apply { this.baseUrl = baseUrl }

        fun build(): NetworkApi = NetworkApi().apply {
            this.baseUrl = this@Builder.baseUrl
        }
    }
    fun getHttpClient(): HttpClient = getHttpClient(
        baseUrl = baseUrl ?: "https://api.twelvedata.com",
    )
}