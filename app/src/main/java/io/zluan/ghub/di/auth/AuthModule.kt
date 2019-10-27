package io.zluan.ghub.di.auth

import dagger.Module
import dagger.Provides
import io.zluan.ghub.network.auth.AuthService
import retrofit2.Retrofit

@Module
class AuthModule {
    @AuthScope
    @Provides
    fun provideAuthService(retrofitBuilder: Retrofit.Builder): AuthService {
        return retrofitBuilder.build().create(AuthService::class.java)
    }
}
