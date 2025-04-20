package com.jetbrains.greeting.shared.utils

data class RestClientResult<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null,
    val errorCode: String? = null,
    val isFromCache: Boolean? = null
) {

    enum class Status {
        NONE,
        LOADING,
        SUCCESS,
        ERROR
    }

    companion object {

        fun <T> none(): RestClientResult<T> {
            return RestClientResult(Status.NONE)
        }
        fun <T> loading(): RestClientResult<T> {
            return RestClientResult(Status.LOADING)
        }
        fun <T> success(data: T, isFromCache: Boolean = false): RestClientResult<T> {
            return RestClientResult(Status.SUCCESS, data, null, isFromCache = isFromCache)
        }
        fun <T> error(
            message: String,
            data: T? = null,
            errorCode: String? = null
        ): RestClientResult<T> {
            return RestClientResult(
                Status.ERROR,
                message = message,
                errorCode = errorCode
            )
        }
    }
}