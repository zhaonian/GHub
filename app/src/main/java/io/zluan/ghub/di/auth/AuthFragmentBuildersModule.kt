package io.zluan.ghub.di.auth

import dagger.Module

@Module
abstract class AuthFragmentBuildersModule {
    // TODO(zluan): thinking about having fragments in the AuthActivity
    // @ContributesAndroidInjector()
    // abstract fun contributeRegisterFragment(): LauncherFragment

    // @ContributesAndroidInjector()
    // abstract fun contributeResetPasswordFragment(): LauncherFragment
}
