package com.fay.inject

import com.fay.core.base.inject.UiScope
import com.fay.presentation.app.CommonAppCreator
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

interface CommonUiComponent {
    val circuit: Circuit
    val commonAppCreator: CommonAppCreator
    val presenterFactories: Set<Presenter.Factory>
    val uiFactories: Set<Ui.Factory>

    @SingleIn(UiScope::class)
    @Provides
    fun circuit(presenterFactories: Set<Presenter.Factory>, uiFactories: Set<Ui.Factory>): Circuit {
        return Circuit.Builder()
            .addPresenterFactories(presenterFactories)
            .addUiFactories(uiFactories)
            .build()
    }
}
