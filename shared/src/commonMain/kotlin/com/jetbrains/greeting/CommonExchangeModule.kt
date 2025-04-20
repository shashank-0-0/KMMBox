package com.jetbrains.greeting

import com.jetbrains.greeting.network.ExchangeDataSource
import com.jetbrains.greeting.network.ExchangeRepository
import io.ktor.client.HttpClient

class CommonExchangeModule(
    client: HttpClient
) {
    val exchangeDataSource: ExchangeDataSource by lazy {
        ExchangeDataSource(client)
    }

    val exchangeRepository: ExchangeRepository by lazy {
        ExchangeRepository(exchangeDataSource)
    }

}