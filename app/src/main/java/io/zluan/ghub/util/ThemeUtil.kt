package io.zluan.ghub.util

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate

/** Utils for setting a theme. */
object ThemeUtil {
    /** Enforce light mode. */
    const val LIGHT_MODE = "light"
    /** Enforce dark mode. */
    const val DARK_MODE = "dark"
    /**
     * Follows the system settings.
     * For >= API 28, there is a system-wide dark mode that can be toggled on by the user. Turning
     * on battery saver will also trigger dark mode.
     * For <  API 28, simply follow the battery saver's setting.
     */
    const val DEFAULT_MODE = "default"

    /** Apply the selected theme. */
    fun applyTheme(theme: String?) {
        val mode = when (theme) {
            LIGHT_MODE -> AppCompatDelegate.MODE_NIGHT_NO
            DARK_MODE -> AppCompatDelegate.MODE_NIGHT_YES
            else -> {
                when {
                    Build.VERSION.SDK_INT >= 28 -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    else -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
                }
            }
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}
