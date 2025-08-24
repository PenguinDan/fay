package com.fay.core.authentication

import com.fay.core.base.inject.ApplicationScope
import com.fay.core.network.ktor.safeApiCall
import com.fay.core.network.session.SessionTokenProvider
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import me.tatarka.inject.annotations.Inject
import software.amazon.lastmile.kotlin.inject.anvil.ContributesBinding
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@SingleIn(ApplicationScope::class)
@Inject
@ContributesBinding(scope = ApplicationScope::class, boundType = AuthManager::class)
@ContributesBinding(scope = ApplicationScope::class, boundType = SessionTokenProvider::class)
class AuthManagerImpl(
    private val httpClient: HttpClient,
) : AuthManager, SessionTokenProvider {
    private var sessionToken: String? = null

    override fun getSessionToken(): String? {
        return sessionToken
    }

    override suspend fun login(username: String, password: String): Boolean {
        val loginCredentials = LoginCredentials(username, password)
        return safeApiCall {
            httpClient
                .post("/signin") { setBody(loginCredentials) }
                .body<SessionToken>()
        }.onSuccess {
            // Store token upon successful login
            sessionToken = it.token
        }.isSuccess
    }
}