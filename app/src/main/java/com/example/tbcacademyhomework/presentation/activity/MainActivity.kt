package com.example.tbcacademyhomework.presentation.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.ActivityMainBinding
import com.example.tbcacademyhomework.presentation.core.managers.language.getLanguageManager
import com.example.tbcacademyhomework.presentation.core.util.visibleIf
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !mainViewModel.themeSet
            }
        }
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleEdgeToEdge()
        initBottomNavigation()
        initObserver()


    }

    private fun initBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.graph = navController.navInflater.inflate(R.navigation.nav_graph)

        binding.bottomMenu.setupWithNavController(navController)
        binding.bottomMenu.setOnApplyWindowInsetsListener(null)

        binding.bottomMenu.setOnItemReselectedListener { }

        binding.bottomMenu.setOnItemSelectedListener { item ->
            navController.navigate(
                item.itemId,
                null,
                NavOptions.Builder()
                    .setLaunchSingleTop(true)
                    .setPopUpTo(
                        R.id.homeFragment,
                        inclusive = false,
                        saveState = true
                    )
                    .setRestoreState(true)
                    .build()
            )
            true
        }

        appBarConfiguration = AppBarConfiguration(
            getVisibleNavFragmentIds().toSet()
        )

        setBottomNavBarVisibility()
    }

    private fun getVisibleNavFragmentIds(): List<Int> {
        return listOf(
            R.id.homeFragment,
            R.id.favouritesFragment,
            R.id.settingsFragment
        )
    }

    private fun setBottomNavBarVisibility() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomMenu.visibleIf(
                destination.id in getVisibleNavFragmentIds()
            )
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.restartActivityEvent.collect {
                    recreate()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }


    private fun handleEdgeToEdge() {
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
            val bottomPadding = if (imeInsets.bottom == 0) systemBars.bottom else imeInsets.bottom
            view.updatePadding(
                left = systemBars.left,
                top = systemBars.top,
                right = systemBars.right,
                bottom = bottomPadding
            )
            insets
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.getLanguageManager?.setLanguage(newBase))
    }
}