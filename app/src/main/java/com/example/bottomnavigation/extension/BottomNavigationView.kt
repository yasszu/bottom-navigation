package com.example.bottomnavigation.extension

import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.util.Log

/**
 * Created by Yasuhiro Suzuki on 2017/07/15.
 *
 * http://stackoverflow.com/questions/40176244/how-to-disable-bottomnavigationview-shift-mode
 */

fun BottomNavigationView.disableShiftMode() {
    val menuView = getChildAt(0) as BottomNavigationMenuView
    try {
        val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
        shiftingMode.isAccessible = true
        shiftingMode.setBoolean(menuView, false)
        shiftingMode.isAccessible = false
        for (i in 0..menuView.childCount - 1) {
            val item = menuView.getChildAt(i) as BottomNavigationItemView
            item.setShiftingMode(false)
            item.setChecked(item.itemData.isChecked)
        }
    } catch (e: NoSuchFieldException) {
        Log.e("BottomNavigationHelper", "Unable to get shift mode field", e)
    } catch (e: IllegalAccessException) {
        Log.e("BottomNavigationHelper", "Unable to change value of shift mode", e)
    }
}

fun BottomNavigationView.active(position: Int) {
    menu.getItem(position).isChecked = true
}