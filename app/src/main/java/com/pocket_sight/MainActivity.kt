package com.pocket_sight

import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.pocket_sight.databinding.ActivityMainBinding
import com.pocket_sight.fragments.accounts.RemoveAccountDialogFragment
import com.pocket_sight.fragments.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    //private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home_fragment,
                R.id.stats_fragment,
                R.id.recurring_acts_fragment,
                R.id.accounts_fragment,
                R.id.categories_fragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        //NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        navView.setupWithNavController(navController)
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
        //return NavigationUI.navigateUp(navController, drawerLayout)
    }

    override fun onBackPressed() {
        val homeFragment = supportFragmentManager.findFragmentById(R.id.home_fragment)
        if (homeFragment is HomeFragment) {
            if (homeFragment.fabIsExpanded) {
                homeFragment.shrinkFab()
            } else  {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }

    //override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
     //   val homeFragment = supportFragmentManager.findFragmentById(R.id.home_fragment)
        //val homeFragment = supportFragmentManager.findFragmentById(R.id.home_fragment) as? HomeFragment
        //if (homeFragment != null && ev?.action == MotionEvent.ACTION_DOWN) {
        //    if (homeFragment.fabIsExpanded) {
        //        val outRect = Rect()
        //        homeFragment.binding.fabConstraintLayout.getGlobalVisibleRect(outRect)
        //        if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
        //            homeFragment.shrinkFab()
        //        }
        //    }
        //}

      //  return super.dispatchTouchEvent(ev)
    //}



}
