package com.jetbrains.greeting.shared

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.cache.storage.CacheStorage

interface HttpEngineProvider {
    fun clientEngine(
        shouldEnableLogging: Boolean,
    ): HttpClientEngine
}