package com.machmudow.shoppinglists.utils

import java.lang.Exception

object ValidationUtils {

    fun validateTitle(
        title: String
    ): Boolean {
        if (title.isBlank())
            return false
        return true
    }

    fun validateQuantity(
        quantity: String,
    ): Boolean {
        try {
            if (quantity.toInt() < 1)
                return false
        } catch (e: Exception) {
            return false
        }
        return true
    }
}