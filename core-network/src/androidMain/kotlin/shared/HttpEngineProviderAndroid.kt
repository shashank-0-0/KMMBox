package shared

import com.jetbrains.greeting.shared.HttpEngineProvider
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.engine.okhttp.OkHttpEngine

class HttpEngineProviderAndroid: HttpEngineProvider {
    override fun clientEngine(shouldEnableLogging: Boolean): HttpClientEngine {
        return OkHttp.create {  }
    }
}