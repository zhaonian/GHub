package io.zluan.ghub.network.auth

import androidx.lifecycle.LiveData
import io.zluan.ghub.model.AuthToken
import io.zluan.ghub.util.ApiResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthService {
    @Headers(value= ["Accept: application/json"])
    @POST(value = "login/oauth/access_token")
    @FormUrlEncoded
    fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ): LiveData<ApiResponse<AuthToken>>
}
