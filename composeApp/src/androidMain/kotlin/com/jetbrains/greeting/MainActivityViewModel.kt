package com.jetbrains.greeting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        getStocksData()
    }

    fun getStocksData() {
        viewModelScope.launch() {
            val networkApiClient = NetworkApi.Builder()
                .build()
                .getHttpClient()
            ExchangeRepository(
                ExchangeDataSource(networkApiClient)
            ).getStockExchangesList()
                .collectUnwrapped(
                    onLoading = {
                        println("SEEHERE LOADING ")
                    },
                    onSuccess = {
                        println("SEEHERE success ${it}")
                    }
                )


            /*val client = HttpClient(
                OkHttp.create {
                    addInterceptor(ChuckerInterceptor(getApplication<Application>().applicationContext))
                }
            ){
                //Serialization
                install(ContentNegotiation) {
                    json(Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                        useAlternativeNames = true
                        encodeDefaults = true
                        explicitNulls = false
                    }
                    )
                }
                install(Logging){
                    level = LogLevel.ALL
                }
            }*/


//            println("SEEHERE client $client")
//            val response= getResult<ApiResponseWrapper<List<Exchange>>> {
//                client.get {
//                    url("https://api.twelvedata.com/exchanges")
//                }
//            }
//            println("SEEHERE client $response")
        }
    }
}

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