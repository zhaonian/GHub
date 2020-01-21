package io.zluan.ghub.di.auth

import dagger.Module
import dagger.Provides
import io.zluan.ghub.network.auth.AuthService
import io.zluan.ghub.persistence.AccountDao
import io.zluan.ghub.persistence.AuthTokenDao
import io.zluan.ghub.repository.auth.AuthRepository
import io.zluan.ghub.session.SessionManager
import retrofit2.Retrofit

@Module
class AuthModule {
    @AuthScope
    @Provides
    fun provideAuthService(retrofitBuilder: Retrofit.Builder): AuthService {
        return retrofitBuilder.build().create(AuthService::class.java)
    }

    @AuthScope
    @Provides
    fun provideAuthRepository(
        authTokenDao: AuthTokenDao,
        accountDao: AccountDao,
        authService: AuthService
    ): AuthRepository {
        return AuthRepository(
            authTokenDao,
            accountDao,
            authService
        )
    }
}
