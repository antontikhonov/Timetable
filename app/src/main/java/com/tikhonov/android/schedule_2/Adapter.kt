package com.tikhonov.android.schedule_2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    override fun getCount(): Int {
        return Integer.MAX_VALUE
    }

    override fun getItem(position: Int): Fragment =
        when (position % 6) {
            1 -> DayFragment("Вторник")
            2 -> DayFragment("Среда")
            3 -> DayFragment("Четверг")
            4 -> DayFragment("Пятница")
            5 -> DayFragment("Суббота")
            else -> DayFragment("Понедельник")
        }
}