package com.jetbrains.greeting.network

import com.jetbrains.greeting.shared.NetworkApi
import io.ktor.client.HttpClient


class CommonKtorNetworkModule() {

    val appNetworkApi: NetworkApi by lazy {
        NetworkApi.Builder()
            .build()
    }
    val appHttpClient: HttpClient by lazy {
        appNetworkApi.getHttpClient()
    }
}