package io.zluan.ghub.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import io.zluan.ghub.R
import io.zluan.ghub.ui.main.MainActivity
import io.zluan.ghub.viewmodel.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_login.loginBtn
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
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
