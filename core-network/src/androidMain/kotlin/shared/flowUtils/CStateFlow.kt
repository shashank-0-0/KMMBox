package com.jetbrains.greeting.shared.flowUtils

import kotlinx.coroutines.flow.StateFlow

actual class CStateFlow<T> actual constructor(
    private val flow: StateFlow<T>
) : StateFlow<T> by flow