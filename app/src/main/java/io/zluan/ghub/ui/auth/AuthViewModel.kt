package io.zluan.ghub.ui.auth

import androidx.lifecycle.ViewModel
import io.zluan.ghub.di.coroutines.IODispatcher
import io.zluan.ghub.repository.auth.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/** A ViewModel for all authentication-related work. */
class AuthViewModel @Inject constructor(
    val authRepository: AuthRepository,
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
