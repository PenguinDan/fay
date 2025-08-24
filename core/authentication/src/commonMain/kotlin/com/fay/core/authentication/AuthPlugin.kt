package com.fay.core.authentication

interface AuthPlugin {
    suspend fun onLogin()
    suspend fun onLogout()
}
