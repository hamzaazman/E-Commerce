package com.example.e_commerce

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.e_commerce.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpWithNavigationBar()
    }

    private fun setUpWithNavigationBar() {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        NavigationUI.setupWithNavController(binding.bottomNavMain, navHost.findNavController())

        //todo: this listener for only debug purpose, you should remove later.
        navHost.findNavController()
            .addOnDestinationChangedListener(object : NavController.OnDestinationChangedListener {
                override fun onDestinationChanged(
                    controller: NavController,
                    destination: NavDestination,
                    arguments: Bundle?
                ) {
                    println("tugce-current: ${controller.currentBackStackEntry?.destination?.displayName}")
                    println("tugce-back: ${controller.previousBackStackEntry?.destination?.displayName}")
                }
            })

        //Here is the solution code block, you can refactor it whatever you want
        this.onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (findNavController(R.id.fragmentContainerView).currentDestination?.id == R.id.mainFragment) {
                    this@MainActivity.finish()
                } else if (findNavController(R.id.fragmentContainerView).previousBackStackEntry != null) {
                    findNavController(R.id.fragmentContainerView).popBackStack()
                } else {
                    this@MainActivity.finish()
                }
            }
        })
    }
}
