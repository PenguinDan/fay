package com.fay.inject

import com.fay.core.authentication.CoreAuthenticationModuleComponent
import com.fay.core.base.inject.ApplicationScope
import software.amazon.lastmile.kotlin.inject.anvil.MergeComponent
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@MergeComponent(ApplicationScope::class)
@SingleIn(ApplicationScope::class)
abstract class IOSApplicationComponent() : CommonApplicationComponent, CoreAuthenticationModuleComponent

@MergeComponent.CreateComponent
expect fun createComponent(): IOSApplicationComponent
