package com.fay.core.authentication

import kotlinx.serialization.Serializable

@Serializable
data class SessionToken(val token: String)