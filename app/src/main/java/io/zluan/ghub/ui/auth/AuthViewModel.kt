package io.zluan.ghub.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.zluan.ghub.di.coroutines.IODispatcher
import io.zluan.ghub.model.AuthToken
import io.zluan.ghub.repository.auth.AuthRepository
import io.zluan.ghub.util.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
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

    // TODO: need to clean this up with a better architecture.
    private var _authTokenLiveData: LiveData<ApiResponse<AuthToken>>? = null
    /** The auth token returned from Github after the user logged in in the oauth webview. */
    val authTokenLiveData: LiveData<ApiResponse<AuthToken>>
        get() = _authTokenLiveData ?: MutableLiveData()

    fun fetchAccessToken(
        clientId: String,
        clientSecret: String,
        code: String
    ) {
        launch {
            _authTokenLiveData = authRepository.fetchAccessToken(
                clientId = clientId,
                clientSecret = clientSecret,
                code = code
            )
        }
    }
}
