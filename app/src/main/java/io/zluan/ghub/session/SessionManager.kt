package io.zluan.ghub.session

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.zluan.ghub.di.coroutines.IODispatcher
import io.zluan.ghub.di.coroutines.MainDispatcher
import io.zluan.ghub.model.AuthToken
import io.zluan.ghub.persistence.AuthTokenDao
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/** Manages the authentication scopes. */
@Singleton
class SessionManager @Inject constructor(
    val application: Application,
    private val authTokenDao: AuthTokenDao,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher
) : CoroutineScope {
    private val _cachedToken = MutableLiveData<AuthToken>()

    /** [AuthToken] of the current logged-in user. */
    val cachedToken: LiveData<AuthToken>
        get() = _cachedToken

    private val authenticationJob = Job()
    override val coroutineContext: CoroutineContext
        get() = authenticationJob + ioDispatcher

    /** Logs the user in and updates the auth token in db. */
    fun login(newToken: AuthToken) {
        Timber.d("Logging in...")
        launch(ioDispatcher) {
            authTokenDao.insert(newToken)
        }
        setAuthToken(newToken)
    }

    /** Logs the user out and nullifies the auth token in db. */
    fun logout() {
        Timber.d("Logging out...")
        launch(ioDispatcher) {
            var errorMessage: String? = null
            try {
                _cachedToken.value?.account_pk?.let { authTokenDao.nullifyToken(it) }
                    ?: throw CancellationException("Token Error. Logging out user.")
            } catch (e: CancellationException) {
                Timber.e("logout: ${e.message}")
                errorMessage = e.message
            } catch (e: Exception) {
                Timber.e("logout: ${e.message}")
                errorMessage = errorMessage + "\n" + e.message
            } finally {
                errorMessage?.let { Timber.e("logout: $errorMessage" ) }
                setAuthToken(null)
            }
        }
    }

    /** Sets the auth token to a [newToken]. */
    private fun setAuthToken(newToken: AuthToken?) {
        launch(mainDispatcher) {
            if (_cachedToken.value != newToken) {
                _cachedToken.value = newToken
            }
        }
    }
}
