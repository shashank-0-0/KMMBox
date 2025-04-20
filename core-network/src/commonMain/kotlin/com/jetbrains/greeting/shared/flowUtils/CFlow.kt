package com.jetbrains.greeting.shared.flowUtils

import kotlinx.coroutines.flow.Flow

expect class CFlow<T>(flow: Flow<T>) : Flow<T>

fun <T> Flow<T>.toCommonFlow() = CFlow(this)