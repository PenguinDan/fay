package com.fay

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fay.inject.AndroidUiComponent
import com.fay.presentation.app.CommonApp

class MainActivity : ComponentActivity() {
    private val applicationComponent by lazy {
        (application as FayApplication).applicationComponent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val androidUiComponent = (applicationComponent as AndroidUiComponent.Factory).createAndroidUiComponent(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
        )

        val commonApp = androidUiComponent.commonAppCreator(
            CommonApp.Configuration(onRootPopped = { finish() }),
        )

        setContent {
            commonApp.Screen()
        }
    }
}
