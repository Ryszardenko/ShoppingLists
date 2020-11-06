package com.machmudow.shoppinglists.utils

import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.machmudow.shoppinglists.R

object ButtonUtils {

    fun MaterialButton.enableBtn() {
        isClickable = true
        isEnabled = true
        setBackgroundColor(ContextCompat.getColor(context, R.color.light_green))
    }

    fun MaterialButton.disableBtn() {
        isClickable = false
        isEnabled = false
        setBackgroundColor(ContextCompat.getColor(context, R.color.grey_chateau))
    }
}