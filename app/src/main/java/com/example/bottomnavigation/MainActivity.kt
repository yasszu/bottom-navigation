package com.example.bottomnavigation

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.example.bottomnavigation.fragment.DashboardFragment
import com.example.bottomnavigation.fragment.HomeFragment
import com.example.bottomnavigation.fragment.NotificationsFragment
import com.example.bottomnavigation.fragment.ProfileFragment
import com.example.bottomnavigation.helper.BottomNavigationHelper
import com.example.bottomnavigation.helper.BottomNavigationPosition
import com.example.bottomnavigation.extension.*


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val KEY_POSITION = "keyPosition"

    private var navPosition: BottomNavigationPosition = BottomNavigationPosition.HOME

    private lateinit var toolbar: Toolbar

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
        restoreSaveInstanceState(savedInstanceState)
        setSupportActionBar(toolbar)
        setupBottomNavigation()
        initFragment(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt(KEY_POSITION, navPosition.id)
        super.onSaveInstanceState(outState)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        navPosition = BottomNavigationHelper.findPositionById(item.itemId)
        return when (item.itemId) {
            R.id.home -> switchFragment(HomeFragment.newInstance(), HomeFragment.TAG)
            R.id.dashboard -> switchFragment(DashboardFragment.newInstance(), DashboardFragment.TAG)
            R.id.notifications -> switchFragment(NotificationsFragment.newInstance(), NotificationsFragment.TAG)
            R.id.profile -> switchFragment(ProfileFragment.newInstance(), ProfileFragment.TAG)
            else -> false
        }
    }

    private fun restoreSaveInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.also {
            val id = it.getInt(KEY_POSITION, BottomNavigationPosition.HOME.id)
            navPosition = BottomNavigationHelper.findPositionById(id)
        }
    }

    private fun bindViews() {
        toolbar = findViewById(R.id.toolbar)
        bottomNavigation = findViewById(R.id.bottom_navigation)
    }

    private fun setupBottomNavigation() {
        bottomNavigation.disableShiftMode()
        bottomNavigation.active(navPosition.position)
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        savedInstanceState ?: switchFragment(HomeFragment.newInstance(), HomeFragment.TAG)
    }

    /**
     * Immediately execute transactions with FragmentManager#executePendingTransactions.
     */
    private fun switchFragment(fragment: Fragment, tag: String): Boolean {
        if (fragment.isAdded) return false
        detachFragment()
        attachFragment(fragment, tag)
        supportFragmentManager.executePendingTransactions()
        return true
    }

    private fun detachFragment() {
        supportFragmentManager.findFragmentById(R.id.container)?.also {
            supportFragmentManager.beginTransaction().detach(it).commit()
        }
    }

    private fun attachFragment(fragment: Fragment, tag: String) {
        if (fragment.isDetached) {
            supportFragmentManager.beginTransaction().attach(fragment).commit()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.container, fragment, tag).commit()
        }
        supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
    }

}
