package com.fay.core.network.networkconfiguration

import com.fay.core.base.inject.ApplicationScope
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding

interface NetworkConfig {
    val url: String
}

@Inject
@ContributesBinding(scope = ApplicationScope::class)
class NetworkConfigImpl : NetworkConfig {
    override val url: String
        get() = "https://node-api-for-candidates.onrender.com"
}