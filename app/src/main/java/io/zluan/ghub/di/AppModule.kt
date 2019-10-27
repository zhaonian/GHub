package io.zluan.ghub.di

import dagger.Module
import dagger.Provides
import io.zluan.ghub.network.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/** Application-wide dependencies. */
@Module
class AppModule {
    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.GITHUB_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
    }
}
