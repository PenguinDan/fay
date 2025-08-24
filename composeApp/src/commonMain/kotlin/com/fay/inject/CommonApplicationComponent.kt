package com.fay.inject

import com.fay.core.base.CoreBaseModuleComponent
import com.fay.initializer.RootAppInitializer

interface CommonApplicationComponent : CoreBaseModuleComponent {
    val rootAppInitializer: RootAppInitializer
}
