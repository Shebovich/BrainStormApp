package com.brainshtorm.brainstorm.data

import android.content.Context
import com.brainshtorm.brainstorm.SessionManager
import java.util.*

data class DayProgress(val imageProgress: Int, val numberDay: Int, val colorDay: Int) {
    private val dayMap = mapOf(getCurrentDay() to 1, getCurrentDay()+1 to 2,
            getCurrentDay()+2 to 3, getCurrentDay()+3 to 4,
            getCurrentDay()+4 to 5, getCurrentDay()+5 to 6,
            getCurrentDay()+6 to 7)

    companion object {
        val MILLIS_IN_DAY = 1000 * 60 * 60 * 24
        fun getCurrentDay(): Long{
            val cal: Calendar = Calendar.getInstance()
            val year: Int = cal.get(Calendar.YEAR)
            val month: Int = cal.get(Calendar.MONTH)
            val date: Int = cal.get(Calendar.DATE)
            cal.clear()
            cal.set(year, month, date)
            return cal.getTimeInMillis()/ MILLIS_IN_DAY
        }
        fun getNextDay(firstDay: Int): Long{
           return  getCurrentDay()-firstDay
        }
    }
}