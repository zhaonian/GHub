package io.zluan.ghub.ui.auth.state

sealed class AuthStateEvent {
    class GetAuthTokenEvent: AuthStateEvent()
    class CheckLoggedInEvent: AuthStateEvent()
}
