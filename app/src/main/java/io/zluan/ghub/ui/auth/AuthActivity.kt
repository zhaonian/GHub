package io.zluan.ghub.ui.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import io.zluan.ghub.BuildConfig
import io.zluan.ghub.R
import io.zluan.ghub.viewmodel.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_login.loginBtn
import timber.log.Timber
import javax.inject.Inject

/** An Activity for controlling the authentication screen. */
class AuthActivity : DaggerAppCompatActivity() {
    @Inject lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)

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
        if (intent?.data != null) {

        }
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
