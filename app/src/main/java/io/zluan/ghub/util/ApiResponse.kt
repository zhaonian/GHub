package io.zluan.ghub.util

import retrofit2.Response
import timber.log.Timber

/**
 * Common class used by API responses.
 * @param <T> the type of the response object</T>
 *
 * https://github.com/android/architecture-components-samples/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/api/ApiResponse.kt
 */
@Suppress("unused") // T is used in extending classes
sealed class ApiResponse<T> {
    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(
                error.message ?: "unknown error"
            )
        }

        fun <T> create(response: Response<T>): ApiResponse<T> {
            Timber.d("GenericApiResponse: response: $response")
            Timber.d("GenericApiResponse: raw: ${response.raw()}")
            Timber.d("GenericApiResponse: headers: ${response.headers()}")
            Timber.d("GenericApiResponse: message: ${response.message()}")
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body = body)
                }
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ApiErrorResponse(errorMsg)
            }
        }
    }
}

/**
 * separate class for HTTP 204 responses so that we can make ApiSuccessResponse's body non-null.
 */
class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()
