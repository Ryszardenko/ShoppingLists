package com.machmudow.shoppinglists.utils

import android.content.Context
import android.widget.Toast

object ToastUtils {

    fun Context.showShortToast(message: String?) {
        if (message != null && message.isNotBlank())
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    fun Context.showShortToast(messageResId: Int) {
        val message = getString(messageResId)
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    fun Context.showLongToast(message: String?) {
        if (message != null && message.isNotBlank())
            Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }
}