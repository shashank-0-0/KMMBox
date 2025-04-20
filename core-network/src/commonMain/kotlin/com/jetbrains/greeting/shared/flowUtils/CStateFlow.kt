package com.jetbrains.greeting.shared.flowUtils

import kotlinx.coroutines.flow.StateFlow

expect class CStateFlow<T>(flow: StateFlow<T>): StateFlow<T>

fun <T> StateFlow<T>.toCommonStateFlow() = CStateFlow(this)