package com.fay.inject

import android.app.Activity
import com.fay.core.base.inject.ApplicationScope
import com.fay.core.base.inject.UiScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesSubcomponent
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@ContributesSubcomponent(UiScope::class)
@SingleIn(UiScope::class)
interface AndroidUiComponent : CommonUiComponent {
    @ContributesSubcomponent.Factory(ApplicationScope::class)
    interface Factory {
        fun createAndroidUiComponent(activity: Activity): AndroidUiComponent
    }
}
