package com.fay.core.network.ktor

import com.fay.core.base.inject.ApplicationScope
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo

@ContributesTo(ApplicationScope::class)
interface KtorComponentIOS {
    @Provides
    fun darwinHttpClientEngine(): HttpClientEngine = Darwin.create()
}