package com.example.bottomnavigation.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bottomnavigation.R


class NotificationsFragment : Fragment() {

    companion object {
        val TAG: String = NotificationsFragment::class.java.simpleName
        fun newInstance() = NotificationsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.title_notifications)
        val view = inflater.inflate(R.layout.fragment_notifications, container, false)
        return view
    }

}