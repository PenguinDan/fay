package com.fay.core.network.session

interface SessionTokenProvider {
    fun getSessionToken(): String?
}