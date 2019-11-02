package io.zluan.ghub.ui.auth

import androidx.lifecycle.ViewModel
import io.zluan.ghub.di.coroutines.IODispatcher
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class AuthViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel(), CoroutineScope {
    private val authenticationJob = Job()
    override val coroutineContext: CoroutineContext
        get() = authenticationJob + ioDispatcher

    fun test() {
        Timber.d("Call authentication REST API and etc here")
        launch {
            Timber.d("hahahaha: start!")
            delay(2_000L)
            Timber.d("hahahaha: end! ${Thread.currentThread().name}")
        }
    }
}
