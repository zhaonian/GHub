package io.zluan.ghub.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import io.zluan.ghub.viewmodel.ViewModelProviderFactory

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}
