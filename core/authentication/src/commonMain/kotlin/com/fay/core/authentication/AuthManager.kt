package com.fay.core.authentication

interface AuthManager {
    suspend fun login(username: String, password: String): Boolean
}
