package io.zluan.ghub.ui.auth.state

import io.zluan.ghub.model.AuthToken

data class AuthViewState(
    var authToken: AuthToken? = null
)