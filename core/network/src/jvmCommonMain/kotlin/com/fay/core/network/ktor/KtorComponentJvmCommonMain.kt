package com.fay.core.network.ktor

import com.fay.core.base.inject.ApplicationScope
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo

@ContributesTo(ApplicationScope::class)
interface KtorComponentJvmCommonMain {
    @Provides
    fun okhttpHttpClientEngine(): HttpClientEngine = OkHttp.create()
}