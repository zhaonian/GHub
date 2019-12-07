package io.zluan.ghub.di

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import io.zluan.ghub.network.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/** Application-wide dependencies. */
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.GITHUB_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
    }

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.builder()
            .serverUrl(Constants.GITHUB_GRAPHQL_BASE_URL)
            .build()
    }
}
