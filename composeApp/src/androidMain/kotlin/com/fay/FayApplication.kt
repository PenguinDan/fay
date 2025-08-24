package com.fay

import android.app.Application
import com.fay.inject.AndroidApplicationComponent
import com.fay.inject.create

class FayApplication : Application() {
    private lateinit var applicationComponent: AndroidApplicationComponent

    override fun onCreate() {
        super.onCreate()

        initApplicationComponent()
    }

    private fun initApplicationComponent() {
        applicationComponent = AndroidApplicationComponent::class.create(this)
        // applicationComponent.rootAppInitializer.init()
    }

    fun applicationComponent(): AndroidApplicationComponent = applicationComponent
}
