package com.jetbrains.greeting.network

import com.jetbrains.greeting.shared.utils.ApiResponseWrapper
import com.jetbrains.greeting.shared.utils.BaseDataSource
import com.jetbrains.greeting.shared.utils.BaseRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class ExchangeDataSource(
    private val client: HttpClient
) : BaseDataSource() {

    suspend fun getStockExchangesList() = getResult<ApiResponseWrapper<List<Exchange>?>> {
        client.get {
            url("https://api.twelvedata.com/exchanges")
        }
    }
}

class ExchangeRepository(
    val exchangeDataSource: ExchangeDataSource
): BaseRepository {
    suspend fun getStockExchangesList() =
        getFlowResult {
            exchangeDataSource.getStockExchangesList()
        }
}


@Serializable
data class Exchange(
    @SerialName("title")
    val title: String,
    @SerialName("name")
    val name: String,
    @SerialName("code")
    val code: String,
    @SerialName("country")
    val country: String,
    @SerialName("timezone")
    val timezone: String,
)