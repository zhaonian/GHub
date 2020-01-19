package io.zluan.ghub.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import io.zluan.ghub.BaseApplication
import io.zluan.ghub.di.coroutines.CoroutinesModule
import io.zluan.ghub.session.SessionManager
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        CoroutinesModule::class,
        ActivityBuildersModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {
    // deps added here will be able to be injected into anywhere.

    val sessionManager: SessionManager // add it here in order to inject it into abstract class.

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
