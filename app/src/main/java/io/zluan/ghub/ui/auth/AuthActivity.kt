package io.zluan.ghub.ui.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import io.zluan.ghub.BuildConfig
import io.zluan.ghub.R
import io.zluan.ghub.model.AuthToken
import io.zluan.ghub.session.SessionManager
import io.zluan.ghub.util.ApiResponse
import io.zluan.ghub.util.ApiSuccessResponse
import io.zluan.ghub.viewmodel.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_login.loginBtn
import javax.inject.Inject

/** An Activity for controlling the authentication screen. */
class AuthActivity : DaggerAppCompatActivity() {
    @Inject lateinit var providerFactory: ViewModelProviderFactory
    @Inject lateinit var sessionManager: SessionManager
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
        viewModel.authTokenLiveData.observe(this, Observer {
            this.handleAccessToken(it)
        })
        loginBtn?.setOnClickListener {
            val uri = Uri.parse(OAUTH_URL)
                .buildUpon()
                .appendQueryParameter(PARAM_CLIENT_ID, BuildConfig.GITHUB_CLIENT_ID)
                .appendQueryParameter(PARAM_SCOPE, SCOPES)
                .appendQueryParameter(PARAM_CALLBACK_URI, CALLBACK_URI.toString())
                .build()
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        }
    }

    override fun onResume() {
        super.onResume()

        val code = intent?.data ?: run {
            Toast.makeText(this, "Github login required :D", Toast.LENGTH_LONG).show()
            return
        }

        viewModel.fetchAccessToken(
            BuildConfig.GITHUB_CLIENT_ID,
            BuildConfig.GITHUB_CLIENT_SECRET,
            code.toString()
        )
    }

    /** Handles the access token returned from the Github OAuth webview. */
    private fun handleAccessToken(authTokenResponse: ApiResponse<AuthToken>) {
        when (authTokenResponse) {
            is ApiSuccessResponse -> {
                sessionManager.login(authTokenResponse.body)
            }
            else -> { /* no-op */ } // TODO: fill this in
        }
    }

    private fun subscribeObservers() {
        viewModel.viewState.observe(this, Observer {

        })
    }

    companion object {
        private const val OAUTH_URL = "https://github.com/login/oauth/authorize"
        private const val PARAM_CLIENT_ID = "client_id"
        private const val PARAM_SCOPE = "scope"
        private const val SCOPES = "repo"
        private const val PARAM_CALLBACK_URI = "redirect_uri"
        private val CALLBACK_URI = Uri.parse("ghub://oauth")
        private const val PARAM_CODE = "code"
    }
}
