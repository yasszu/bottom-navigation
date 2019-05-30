package com.example.bottomnavigation.extension

import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.util.Log

/**
 * Created by Yasuhiro Suzuki on 2017/07/15.
 *
 * This can be used in Support Library 27 or lower.
 * See http://stackoverflow.com/questions/40176244/how-to-disable-bottomnavigationview-shift-mode
 */

fun BottomNavigationView.disableShiftMode() {
    val menuView = getChildAt(0) as BottomNavigationMenuView
    try {
        menuView.javaClass.getDeclaredField("mShiftingMode").also { shiftMode ->
            shiftMode.isAccessible = true
            shiftMode.setBoolean(menuView, false)
            shiftMode.isAccessible = false
        }
        for (i in 0 until menuView.childCount) {
            (menuView.getChildAt(i) as BottomNavigationItemView).also { item ->
                item.setShifting(false)
                item.setChecked(item.itemData.isChecked)
            }
        }
    } catch (t: Throwable) {
        Log.e("BottomNavigationHelper", "Unable to get shift mode field", t)
    } catch (e: IllegalAccessException) {
        Log.e("BottomNavigationHelper", "Unable to change value of shift mode", e)
    }
}

/**
 * Make selected position active
 */
fun BottomNavigationView.active(position: Int) {
    menu.getItem(position).isChecked = true
}