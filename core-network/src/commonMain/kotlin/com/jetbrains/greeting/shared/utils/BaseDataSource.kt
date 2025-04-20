package com.jetbrains.greeting.shared.utils

import com.jetbrains.greeting.shared.utils.ResponseHeader.X_TRACE_ID
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.JsonConvertException
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.SerializationException
import io.ktor.utils.io.errors.IOException

abstract class BaseDataSource {

    protected suspend inline fun <reified T> getResult(call: () -> HttpResponse): RestClientResult<T> {
        var result: HttpResponse? = null
        return try {
            result = call()
            if (result.status == HttpStatusCode.OK) {
                val data: T = result.body()
                if (data is ApiResponseWrapper<*>) {
                    data.success?.let { success->
                        if(success == false){
                            Exception(API_FAILED_WITH_SUCCESS_CODE).printStackTrace()
                        }
                    }
                }
                RestClientResult.success(data)
            } else {
                unknownError(result)
            }
        } catch (e: Exception) {
            handleException(e,result)
        }
    }

    suspend fun <T> BaseDataSource.handleException(
        e: Exception,
        result: HttpResponse?
    ):RestClientResult<T> = when (e) {
        is ClientRequestException ->{
            logErrorStackTrace(e)
            when (val statusCode = e.response.status.value) {
                NetworkErrorCodes.ACCESS_TOKEN_EXPIRED -> {
                    RestClientResult.error(
                        message = NetworkErrorMessages.ACCESS_TOKEN_EXPIRED,
                        errorCode = statusCode.toString()
                    )
                }

                NetworkErrorCodes.REFRESH_TOKEN_EXPIRED -> {
                    RestClientResult.error(
                        message = NetworkErrorMessages.PLEASE_LOGIN_AGAIN,
                        errorCode = statusCode.toString()
                    )
                }

                NetworkErrorCodes.UNUSUAL_ACTIVITY_DETECTED -> {
                    RestClientResult.error(
                        message = NetworkErrorMessages.UNUSUAL_ACTIVITY_DETECTED,
                        errorCode = statusCode.toString()
                    )
                }

                else -> {
                    RestClientResult.error(
                        message = NetworkErrorMessages.SOME_ERROR_OCCURRED,
                        errorCode = statusCode.toString()
                    )
                }
            }
        }
        is ServerResponseException -> {
            logErrorStackTrace(e)
            val statusCode = e.response.status.value
            RestClientResult.error(
                message = NetworkErrorMessages.APP_UNDER_MAINTENANCE,
                errorCode = statusCode.toString()
            )
        }
        is IOException -> {
            when (e) {
                is HttpRequestTimeoutException -> {
                    logErrorStackTrace(
                        e
                    )
                }

                else -> {
                    logErrorStackTrace(
                        e,
                    )

                }
            }
            RestClientResult.error(
                message = NetworkErrorMessages.PLEASE_CHECK_YOUR_INTERNET_CONNECTION,
                errorCode = NetworkErrorCodes.INTERNET_NOT_WORKING.toString()
            )
        }
        is UnresolvedAddressException -> {
            logErrorStackTrace(
                e,
            )
            RestClientResult.error(
                message = NetworkErrorMessages.PLEASE_CHECK_YOUR_INTERNET_CONNECTION,
                errorCode = NetworkErrorCodes.INTERNET_NOT_WORKING.toString()
            )
        }
        is SocketTimeoutException -> {
            logErrorStackTrace(
                e
            )
            RestClientResult.error(
                message = NetworkErrorMessages.PLEASE_CHECK_YOUR_INTERNET_CONNECTION,
                errorCode = NetworkErrorCodes.INTERNET_NOT_WORKING.toString()
            )
        }
        is SerializationException -> {
            logErrorStackTrace(
                e
            )
            RestClientResult.error(
                message = NetworkErrorMessages.DATA_SERIALIZATION_ERROR,
                errorCode = NetworkErrorCodes.DATA_SERIALIZATION_ERROR.toString(),
            )
        }
        is JsonConvertException -> {
            logErrorStackTrace(
                e,
            )
            RestClientResult.error(
                message = NetworkErrorMessages.DATA_SERIALIZATION_ERROR,
                errorCode = NetworkErrorCodes.DATA_SERIALIZATION_ERROR.toString()
            )
        }
        is CancellationException -> {

            logErrorStackTrace(
                e,
            )
            RestClientResult.error(
                message = "",  //This is a special case in which we don't want to show any error
                errorCode = NetworkErrorCodes.NETWORK_CALL_CANCELLED.toString()
            )
        }
        else -> {
            logErrorStackTrace(
                e,
            )
            RestClientResult.error(
                e.message ?: NetworkErrorMessages.SOME_ERROR_OCCURRED,
                errorCode = NetworkErrorCodes.UNKNOWN_ERROR_OCCURRED.toString()
            )
        }
    }
    fun <T> unknownError(result: HttpResponse): RestClientResult<T> =
        RestClientResult.error(
            message = NetworkErrorMessages.SOME_ERROR_OCCURRED,
            errorCode = result.status.value.toString()
        )

    private fun logErrorStackTrace(
        e: Exception,
    ) {
        e.printStackTrace()
    }
}