package io.zluan.ghub.di.coroutines

import javax.inject.Qualifier

/** Dagger qualifier for [kotlinx.coroutines.Dispatchers.Default]. */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DefaultDispatcher
