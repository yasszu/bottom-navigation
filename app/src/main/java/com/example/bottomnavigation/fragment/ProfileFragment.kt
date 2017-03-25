package com.example.bottomnavigation.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bottomnavigation.R


class ProfileFragment : Fragment() {

    companion object {
        val TAG = ProfileFragment::class.java.simpleName!!
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity.title = TAG
        val view = inflater?.inflate(R.layout.fragment_profile, container, false)
        return view
    }

}