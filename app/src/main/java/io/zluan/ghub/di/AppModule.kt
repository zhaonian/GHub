package io.zluan.ghub.di

import android.app.Application
import androidx.room.Room
import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import io.zluan.ghub.network.Constants
import io.zluan.ghub.persistence.AccountDao
import io.zluan.ghub.persistence.AppDatabase
import io.zluan.ghub.persistence.AuthTokenDao
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/** Application-wide dependencies. */
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthTokenDao(db: AppDatabase): AuthTokenDao {
        return db.getAuthTokenDao()
    }

    @Provides
    @Singleton
    fun provideAccountDao(db: AppDatabase): AccountDao {
        return db.getAccountDao()
    }

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
