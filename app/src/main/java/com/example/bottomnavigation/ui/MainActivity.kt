package com.example.bottomnavigation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.bottomnavigation.R
import com.example.bottomnavigation.extension.active
import com.example.bottomnavigation.extension.attach
import com.example.bottomnavigation.extension.detach
import com.example.bottomnavigation.helper.BottomNavigationPosition
import com.example.bottomnavigation.helper.createFragment
import com.example.bottomnavigation.helper.findNavigationPositionById
import com.example.bottomnavigation.helper.getTag
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private var navPosition: BottomNavigationPosition = BottomNavigationPosition.HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreSaveInstanceState(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Toolbar>(R.id.toolbar).apply {
            setSupportActionBar(this)
        }

        findViewById<BottomNavigationView>(R.id.bottom_navigation).apply {
            // This is required in Support Library 27 or lower:
            // bottomNavigation.disableShiftMode()

            active(navPosition.position) // Extension function
            setOnNavigationItemSelectedListener { item ->
                navPosition = findNavigationPositionById(item.itemId)
                switchFragment(navPosition)
            }
        }

        initFragment(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // Store the current navigation position.
        outState.putInt(KEY_POSITION, navPosition.id)
        super.onSaveInstanceState(outState)
    }

    private fun restoreSaveInstanceState(savedInstanceState: Bundle?) {
        // Restore the current navigation position.
        savedInstanceState?.getInt(KEY_POSITION, BottomNavigationPosition.HOME.id)?.also {
            navPosition = findNavigationPositionById(it)
        }
    }

    private fun initFragment(savedInstanceState: Bundle?) {
        savedInstanceState ?: switchFragment(BottomNavigationPosition.HOME)
    }

    /**
     * Immediately execute transactions with FragmentManager#executePendingTransactions.
     */
    private fun switchFragment(navPosition: BottomNavigationPosition): Boolean {
        return findFragment(navPosition).let {
            if (it.isAdded) return false
            supportFragmentManager.detach() // Extension function
            supportFragmentManager.attach(it, navPosition.getTag()) // Extension function
            supportFragmentManager.executePendingTransactions()
        }
    }

    private fun findFragment(position: BottomNavigationPosition): Fragment {
        return supportFragmentManager.findFragmentByTag(position.getTag())
                ?: position.createFragment()
    }


    companion object {
        const val KEY_POSITION = "keyPosition"
    }
}
