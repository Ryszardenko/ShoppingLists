package com.machmudow.shoppinglists.utils

import android.content.Context
import android.widget.Toast

object ToastUtils {

    fun Context.showShortToast(messageResId: Int) {
        val message = getString(messageResId)
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}