package com.jetbrains.greeting.shared.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface BaseRepository {

    suspend fun <T> getFlowResult(call: suspend () -> RestClientResult<T>): Flow<RestClientResult<T>> =
        flow {
            emit(RestClientResult.loading())
            val result = call.invoke()
            emit(result)
        }
}