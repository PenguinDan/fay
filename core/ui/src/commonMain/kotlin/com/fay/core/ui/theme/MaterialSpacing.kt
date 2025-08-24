package com.fay.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Suppress("ConstructorParameterNaming", "PropertyName")
@Immutable
data class MaterialSpacing(
    val zero: Dp,
    val _2xSmall: Dp,
    val _1xSmall: Dp,
    val small: Dp,
    val medium: Dp,
    val large: Dp,
    val _1xLarge: Dp,
    val _2xLarge: Dp,
    val _3xLarge: Dp,
)

@Suppress("UnusedReceiverParameter")
val MaterialTheme.spacing
    get() = MaterialSpacing(
        zero = 0.dp,
        _2xSmall = 4.dp,
        _1xSmall = 8.dp,
        small = 12.dp,
        medium = 16.dp,
        large = 24.dp,
        _1xLarge = 32.dp,
        _2xLarge = 48.dp,
        _3xLarge = 64.dp,
    )