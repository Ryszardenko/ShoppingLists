package com.machmudow.shoppinglists.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun getCurrentYearMonthDayTime(): String {
        val sdf = SimpleDateFormat("yyyy-M-dd hh:mm:ss", Locale.ROOT)
        return sdf.format(Date())
    }
}