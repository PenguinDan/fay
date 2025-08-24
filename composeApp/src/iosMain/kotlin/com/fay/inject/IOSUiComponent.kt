package com.fay.inject

import com.fay.core.base.inject.ApplicationScope
import com.fay.core.base.inject.UiScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesSubcomponent
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@ContributesSubcomponent(UiScope::class)
@SingleIn(UiScope::class)
interface IOSUiComponent : CommonUiComponent {
    @ContributesSubcomponent.Factory(ApplicationScope::class)
    interface Factory {
        fun createIOSUiComponent(): IOSUiComponent
    }
}
