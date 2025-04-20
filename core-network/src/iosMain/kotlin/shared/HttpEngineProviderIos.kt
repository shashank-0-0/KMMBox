package shared

import com.jetbrains.greeting.shared.HttpEngineProvider
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

class HttpEngineProviderIOS(
    private val httpMaximumConnectionsPerHost: Int? = null
): HttpEngineProvider{

    override fun clientEngine(shouldEnableLogging: Boolean): HttpClientEngine {
        if (httpMaximumConnectionsPerHost == null || httpMaximumConnectionsPerHost == 0) {
            return Darwin.create()
        } else {
            return Darwin.create {
                httpMaximumConnectionsPerHost?.let {
                    this.configureSession {
                        this.HTTPMaximumConnectionsPerHost = it.toLong()
                    }
                }
            }
        }
    }
}