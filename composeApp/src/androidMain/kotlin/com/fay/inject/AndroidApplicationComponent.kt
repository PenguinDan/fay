package com.fay.inject

import android.app.Application
import com.fay.core.base.inject.ApplicationScope
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.MergeComponent
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@MergeComponent(ApplicationScope::class)
@SingleIn(ApplicationScope::class)
abstract class AndroidApplicationComponent(
    @get:Provides val application: Application,
) : CommonApplicationComponent
