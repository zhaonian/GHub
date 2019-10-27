package io.zluan.ghub.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.zluan.ghub.di.auth.AuthFragmentBuildersModule
import io.zluan.ghub.di.auth.AuthModule
import io.zluan.ghub.di.auth.AuthScope
import io.zluan.ghub.di.auth.AuthViewModelModule
import io.zluan.ghub.ui.auth.AuthActivity

@Module
abstract class ActivityBuildersModule {
    @AuthScope
    @ContributesAndroidInjector(
        modules = [
            AuthModule::class,
            AuthFragmentBuildersModule::class,
            AuthViewModelModule::class
        ]
    )
    abstract fun contributeAuthActivity(): AuthActivity
}