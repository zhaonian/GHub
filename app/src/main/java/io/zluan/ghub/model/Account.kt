package io.zluan.ghub.model

import com.squareup.moshi.Json

/** User's github account for authentication. */
data class Account(
    @field:Json(name = "username") var username: String? = null,
    @field:Json(name = "password") var password: String? = null
)
