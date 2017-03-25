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


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    val KEY_POSITION = "keyPosition"
    var mNavPosition: BottomNavigationPosition = BottomNavigationPosition.HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        restoreSaveInstanceState(savedInstanceState)
        findViewById(R.id.toolbar).apply { setSupportActionBar(this as Toolbar) }
        findViewById(R.id.bottom_navigation).apply { setupBottomNavigation(this as BottomNavigationView) }
        initFragment(savedInstanceState)
    }

    fun restoreSaveInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            val id = savedInstanceState.getInt(KEY_POSITION, BottomNavigationPosition.HOME.id)
            mNavPosition = BottomNavigationHelper.findPositionById(id)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt(KEY_POSITION, mNavPosition.id)
        super.onSaveInstanceState(outState)
    }

    fun setupBottomNavigation(bottomNavigationView: BottomNavigationView) {
        BottomNavigationHelper.disableShiftMode(bottomNavigationView)
        BottomNavigationHelper.activateMenu(bottomNavigationView, mNavPosition.position)
        bottomNavigationView.setOnNavigationItemSelectedListener (this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        mNavPosition = BottomNavigationHelper.findPositionById(item.itemId)
        return when(item.itemId) {
            R.id.home -> switchFragment(HomeFragment.newInstance(), HomeFragment.TAG)
            R.id.dashboard -> switchFragment(DashboardFragment.newInstance(), DashboardFragment.TAG)
            R.id.notifications -> switchFragment(NotificationsFragment.newInstance(), NotificationsFragment.TAG)
            R.id.profile -> switchFragment(ProfileFragment.newInstance(), ProfileFragment.TAG)
            else -> false
        }
    }

    fun initFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val fragment = HomeFragment.newInstance()
            val tag = HomeFragment.TAG
            switchFragment(fragment, tag)
        }
    }

    fun switchFragment(fragment: Fragment, tag: String): Boolean {
        if (fragment.isAdded) return false
        val ft = supportFragmentManager.beginTransaction()
        supportFragmentManager.findFragmentById(R.id.container)?.let { ft.detach(it) }
        attachFragment(fragment, tag, ft)
        supportFragmentManager.executePendingTransactions()
        return true
    }

    fun attachFragment(fragment: Fragment, tag: String, ft: FragmentTransaction) {
        if (fragment.isDetached) {
            ft.attach(fragment)
        } else {
            ft.add(R.id.container, fragment, tag)
        }
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit()
    }

}
