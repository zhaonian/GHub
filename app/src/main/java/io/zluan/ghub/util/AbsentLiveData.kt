package io.zluan.ghub.util

import androidx.lifecycle.LiveData

/**
 * A LiveData class that has [null] value.
 *
 * Copied from https://github.com/android/architecture-components-samples/blob/master/GithubBrowserSample/app/src/main/java/com/android/example/github/util/AbsentLiveData.kt
 */
class AbsentLiveData<T : Any?> private constructor() : LiveData<T>() {
    init {
        // use post instead of set since this can be created on any thread
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return AbsentLiveData()
        }
    }
}
