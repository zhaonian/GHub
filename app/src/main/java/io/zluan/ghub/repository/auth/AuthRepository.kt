package io.zluan.ghub.repository.auth

import androidx.lifecycle.LiveData
import io.zluan.ghub.model.AuthToken
import io.zluan.ghub.network.auth.AuthService
import io.zluan.ghub.persistence.AccountDao
import io.zluan.ghub.persistence.AuthTokenDao
import io.zluan.ghub.util.ApiResponse
import javax.inject.Inject

/** Repository for all auth-related stuff. */
class AuthRepository @Inject constructor(
    private val authTokenDao: AuthTokenDao,
    private val accountDao: AccountDao,
    private val authService: AuthService
) {
    /** Fetches the access_token from Github. */
    fun fetchAccessToken(
        clientId: String,
        clientSecret: String,
        code: String
    ): LiveData<ApiResponse<AuthToken>> {
        return authService.getAccessToken(
            clientId = clientId,
            clientSecret = clientSecret,
            code = code
        )
    }
}
