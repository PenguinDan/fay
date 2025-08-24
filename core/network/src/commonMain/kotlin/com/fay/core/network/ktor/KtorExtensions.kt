package com.fay.core.network.ktor

suspend inline fun <reified T> safeApiCall(apiCall: suspend () -> T): Result<T> {
    return runCatching { apiCall() }
}