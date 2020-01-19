package io.zluan.ghub.repository.auth

import io.zluan.ghub.network.auth.AuthService
import io.zluan.ghub.persistence.AccountDao
import io.zluan.ghub.persistence.AuthTokenDao
import io.zluan.ghub.session.SessionManager
import javax.inject.Inject

/** Repository for all auth-related  stuff. */
class AuthRepository @Inject constructor(
    val authTokenDao: AuthTokenDao,
    val accountDao: AccountDao,
    val authService: AuthService,
    val sessionManager: SessionManager
) {
    fun loginRequest(email: String, password: String) {
    }
}
