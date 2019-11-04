package io.zluan.ghub.util

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.preference.PreferenceManager

/** Utils for easily setting and getting the SharedPreferences. */
object PreferenceUtil {

    const val THEME_STATUS = "theme_status"

    /** Returns the current status of dark mode. */
    fun getThemeStatus(context: Context): String? =
        getDefaultSharedPreference(context).getString(THEME_STATUS, null)

    /**
     * Sets a shared preference.
     *
     * @param context the context
     * @param key the key of the SharedPreference
     * @param value the value of the SharedPreference
     */
    fun setSharedPreference(context: Context, key: String, value: Any) {
        check(!key.isBlank()) { "Key cannot be blank" }
        val edit: Editor = getDefaultSharedPreference(context).edit()
        when (value) {
            is String -> edit.putString(key, value)
            is Int -> edit.putInt(key, value)
            is Long -> edit.putLong(key, value)
            is Boolean -> edit.putBoolean(key, value)
            is Float -> edit.putFloat(key, value)
            else -> {
                throw IllegalArgumentException(
                    String.format(
                        "Type of value unsupported: key=%s, value=%s",
                        key,
                        value
                    )
                )
            }
        }
        edit.apply()
    }

    /** Helper function to get the default SharedPreference. */
    private fun getDefaultSharedPreference(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)
}
