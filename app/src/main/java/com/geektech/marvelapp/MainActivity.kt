package com.geektech.marvelapp

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.geektech.marvelapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideStatusBar()
        hideActionBar()
        hideBottomNavigation()
    }

    private fun hideBottomNavigation() {
        val bottomFragments = listOf(
            R.id.homeFragment,
            R.id.comicBookFragment,
            R.id.shopFragment,
        )

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment)
        navController.popBackStack(R.id.splashScreenFragment, true)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            navView.isVisible = bottomFragments.contains(destination.id)
        }
        navView.setupWithNavController(navController)
    }

    private fun hideActionBar() {
        supportActionBar?.hide()
    }

    private fun hideStatusBar() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }
}