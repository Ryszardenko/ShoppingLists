package com.machmudow.shoppinglists.utils

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.machmudow.shoppinglists.R

object ButtonUtils {

    fun enableBtn(button: MaterialButton) {
        button.isClickable = true
        button.isEnabled = true
        button.setBackgroundColor(ContextCompat.getColor(button.context, R.color.light_green))
    }

    fun disableBtn(button: MaterialButton) {
        button.isClickable = false
        button.isEnabled = false
        button.setBackgroundColor(ContextCompat.getColor(button.context, R.color.grey_chateau))
    }

    fun enableTv(textView: TextView) {
        textView.isClickable = true
        textView.setTextColor(ContextCompat.getColor(textView.context, R.color.light_green))
    }

    fun disableTv(textView: TextView) {
        textView.isClickable = false
        textView.setTextColor(ContextCompat.getColor(textView.context, R.color.grey_chateau))
    }
}