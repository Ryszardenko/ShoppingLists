package com.machmudow.shoppinglists.utils

import android.graphics.Paint
import android.widget.TextView

object TextViewUtils {

    inline var TextView.strike: Boolean
        set(visible) {
            paintFlags = if (visible) paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            else paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
        get() = paintFlags and Paint.STRIKE_THRU_TEXT_FLAG == Paint.STRIKE_THRU_TEXT_FLAG
}