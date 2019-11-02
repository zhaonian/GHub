package io.zluan.ghub.di.coroutines

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class CoroutinesModule {
    @Provides
    @Singleton
    @MainDispatcher
    fun providesMainThreadDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    @IODispatcher
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    @DefaultDispatcher
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
