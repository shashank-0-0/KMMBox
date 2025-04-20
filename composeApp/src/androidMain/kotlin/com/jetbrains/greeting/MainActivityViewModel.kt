package com.jetbrains.greeting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.greeting.exchange.ExchangeViewModel
import com.jetbrains.greeting.network.CommonKtorNetworkModule
import com.jetbrains.greeting.network.ExchangeDataSource
import com.jetbrains.greeting.network.ExchangeRepository
import com.jetbrains.greeting.shared.NetworkApi
import com.jetbrains.greeting.shared.utils.ApiResponseWrapper
import com.jetbrains.greeting.shared.utils.BaseDataSource
import com.jetbrains.greeting.shared.utils.BaseRepository
import com.jetbrains.greeting.shared.utils.collectUnwrapped
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    val viewModel = ExchangeViewModel(
        CommonExchangeModule(CommonKtorNetworkModule().appHttpClient).exchangeRepository,
        coroutineScope = viewModelScope
    )
    init {
        viewModel
    }

}