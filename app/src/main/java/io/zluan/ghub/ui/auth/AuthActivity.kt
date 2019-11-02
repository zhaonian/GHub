package io.zluan.ghub.ui.auth

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import io.zluan.ghub.R
import io.zluan.ghub.viewmodel.ViewModelProviderFactory
import timber.log.Timber
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {
    @Inject lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
        Timber.plant(Timber.DebugTree())
        viewModel.test()
    }
}
