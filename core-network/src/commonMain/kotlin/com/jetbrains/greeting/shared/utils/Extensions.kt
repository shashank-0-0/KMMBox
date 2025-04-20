package com.jetbrains.greeting.shared.utils

import kotlinx.coroutines.flow.Flow

suspend fun <T> Flow<RestClientResult<ApiResponseWrapper<T>>>.collectUnwrapped(
    onLoading: suspend () -> Unit = {},
    onSuccess: suspend (t: T) -> Unit,
    onError: suspend (message: String, errorCode: String?) -> Unit = { _, _ -> },
    onErrorWithResponse: suspend (message:String,t: ApiResponseWrapper<T>) -> Unit = {_,_->},
    onSuccessWithNullData: suspend () -> Unit = {}
) {
    this.collect {
        when (it.status) {
            RestClientResult.Status.LOADING -> {
                onLoading.invoke()
            }

            RestClientResult.Status.SUCCESS -> {
                it.data?.data?.let { data ->
                    onSuccess(data)
                } ?: run {
                    onSuccessWithNullData()
                }
            }

            RestClientResult.Status.ERROR -> {
                onError.invoke(it.message.orEmpty(), it.errorCode)
                it.data?.let { it1 -> onErrorWithResponse(it.message.orEmpty(),it1) }
            }

            RestClientResult.Status.NONE -> {
                // Do nothing in this case
            }
        }
    }
}

fun Boolean?.orFalse() = this ?: false