package io.zluan.ghub.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.switchmaterial.SwitchMaterial
import io.zluan.ghub.R
import io.zluan.ghub.util.PreferenceUtil
import io.zluan.ghub.util.PreferenceUtil.THEME_STATUS
import io.zluan.ghub.util.ThemeUtil
import io.zluan.ghub.util.ThemeUtil.DARK_MODE
import io.zluan.ghub.util.ThemeUtil.DEFAULT_MODE
import timber.log.Timber

/** The one and only Activity that contains all the fragments related to Github interaction. */
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up theme
        val themeStatus = PreferenceUtil.getThemeStatus(this)
        ThemeUtil.applyTheme(themeStatus)

        setContentView(R.layout.activity_main)
        // Set up Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Set up Floating Action Button
        val fab: FloatingActionButton = findViewById<FloatingActionButton>(R.id.fab).apply {
            setOnClickListener { view ->
                Snackbar.make(
                    view,
                    "Show a circular menu with options when pressed",
                    Snackbar.LENGTH_LONG
                ).setAction("Action", null).show()
            }
        }

        // Set up Drawer
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.nav_home,
            R.id.nav_settings,
            R.id.nav_about
        ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        Timber.plant(Timber.DebugTree())

        // Set up Switch in Drawer
        val switchMenuItem: MenuItem = navView.menu.findItem(R.id.nav_dark_mode)
        (switchMenuItem.actionView as SwitchMaterial).apply {
            isChecked = (DARK_MODE == themeStatus)
            setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    ThemeUtil.applyTheme(DARK_MODE)
                    PreferenceUtil.setSharedPreference(context, THEME_STATUS, DARK_MODE)
                } else {
                    ThemeUtil.applyTheme(DEFAULT_MODE)
                    PreferenceUtil.setSharedPreference(context, THEME_STATUS, DEFAULT_MODE)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
