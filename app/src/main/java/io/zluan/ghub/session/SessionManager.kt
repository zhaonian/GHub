package io.zluan.ghub.session

import android.app.Application
import io.zluan.ghub.persistence.AuthTokenDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    val authToken: AuthTokenDao,
    val application: Application
) {

}
