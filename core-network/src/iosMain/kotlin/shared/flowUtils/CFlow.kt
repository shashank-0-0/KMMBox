package com.jetbrains.greeting.shared.flowUtils


import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.EmptyCoroutineContext

actual open class CFlow<T> actual constructor(
    private val flow: Flow<T>
) : Flow<T> by flow {

    private fun subscribe(
        coroutineScope: CoroutineScope, dispatcher: CoroutineDispatcher, onCollect: (T) -> Unit
    ): DisposableHandle {
        val job = coroutineScope.launch(dispatcher) {
            flow.collect(onCollect)
        }
        return DisposableHandle { job.cancel() }
    }

    fun subscribe(
        onCollect: (T) -> Unit
    ): DisposableHandle {
        return subscribe(
            coroutineScope = CoroutineScope(EmptyCoroutineContext),
            dispatcher = Dispatchers.Main,
            onCollect = onCollect
        )
    }
}