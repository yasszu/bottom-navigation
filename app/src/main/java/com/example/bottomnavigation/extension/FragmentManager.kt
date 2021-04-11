package com.example.bottomnavigation.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit
import com.example.bottomnavigation.R

fun FragmentManager.switchFragment(fragment: Fragment, tag: String): Boolean {
    if (fragment.isAdded) return false
    commit {
        // Detach a fragment
        findFragmentById(R.id.container)?.also {
            detach(it)
        }
        // Attach or add a fragment
        if (fragment.isDetached) {
            attach(fragment)
        } else {
            add(R.id.container, fragment, tag)
        }
        // Set the animation for this transaction
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
    }
    // Immediately execute transactions
    return executePendingTransactions()
}