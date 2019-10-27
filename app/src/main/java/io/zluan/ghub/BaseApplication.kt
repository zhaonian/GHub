package io.zluan.ghub

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.zluan.ghub.di.DaggerAppComponent

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}
