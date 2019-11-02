package io.zluan.ghub.di.coroutines

import javax.inject.Qualifier

/** Dagger qualifier for [kotlinx.coroutines.Dispatchers.IO]. */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IODispatcher
