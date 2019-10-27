package io.zluan.ghub.network.auth

import kotlinx.coroutines.flow.Flow
import retrofit2.http.Field

/**
 * TODO(zluan): looks like there are 2 ways to authenticate, plain username/password vs. OAuth
 */
interface AuthService {
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Flow
}
