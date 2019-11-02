package io.zluan.ghub.di.coroutines

import javax.inject.Qualifier

/** Dagger qualifier for [kotlinx.coroutines.Dispatchers.Main]. */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MainDispatcher
