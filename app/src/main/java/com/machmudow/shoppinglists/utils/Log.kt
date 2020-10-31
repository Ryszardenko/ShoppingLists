package com.machmudow.shoppinglists.utils

import android.util.Log

object Log {
    private val buildConfigDebug: Boolean = com.machmudow.shoppinglists.BuildConfig.DEBUG

    fun i(string: String) {
        if (buildConfigDebug) Log.i("ShoppingListDebug", string)
    }

    fun e(string: String) {
        if (buildConfigDebug) Log.e("ShoppingListDebug", string)
    }

    fun d(string: String) {
        if (buildConfigDebug) Log.d("ShoppingListDebug", string)
    }

    fun v(string: String) {
        if (buildConfigDebug) Log.v("ShoppingListDebug", string)
    }

    fun w(string: String) {
        if (buildConfigDebug) Log.w("ShoppingListDebug", string)
    }
}