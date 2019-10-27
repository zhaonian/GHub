package io.zluan.ghub.repository.auth

import io.zluan.ghub.network.auth.AuthService
import javax.inject.Inject

class AuthRepository @Inject constructor(val authService: AuthService) {
    fun loginRequest(email: String, password: String) {

    }
}
