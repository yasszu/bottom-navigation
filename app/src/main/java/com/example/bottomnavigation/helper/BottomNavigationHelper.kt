package com.example.bottomnavigation.helper


/**
 * Created by Yasuhiro Suzuki on 2017/03/12.
 *
 */
object BottomNavigationHelper {

    fun findPositionById(id: Int): BottomNavigationPosition = when(id) {
        BottomNavigationPosition.HOME.id -> BottomNavigationPosition.HOME
        BottomNavigationPosition.DASHBOARD.id -> BottomNavigationPosition.DASHBOARD
        BottomNavigationPosition.NOTIFICATIONS.id -> BottomNavigationPosition.NOTIFICATIONS
        BottomNavigationPosition.PROFILE.id -> BottomNavigationPosition.PROFILE
        else -> BottomNavigationPosition.HOME
    }

}