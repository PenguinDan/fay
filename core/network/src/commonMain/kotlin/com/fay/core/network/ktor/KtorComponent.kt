package com.fay.core.network.ktor

import com.fay.core.base.inject.ApplicationScope
import com.fay.core.network.networkconfiguration.NetworkConfig
import com.fay.core.network.session.SessionTokenProvider
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@ContributesTo(ApplicationScope::class)
interface KtorComponent {
    @SingleIn(ApplicationScope::class)
    @Provides
    fun ktorHttpClient(
        platformEngine: HttpClientEngine,
        networkConfig: NetworkConfig,
        sessionTokenProvider: Lazy<SessionTokenProvider>,
    ) = HttpClient(platformEngine) {
        expectSuccess = true

        Logging {
            level = LogLevel.BODY
            logger = object : Logger {
                override fun log(message: String) {
                    co.touchlab.kermit.Logger.d { message }
                }
            }
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }

        defaultRequest {
            url(networkConfig.url)
            contentType(ContentType.Application.Json)

            header("Authorization", "Bearer ${sessionTokenProvider.value.getSessionToken()}")
        }
    }
}