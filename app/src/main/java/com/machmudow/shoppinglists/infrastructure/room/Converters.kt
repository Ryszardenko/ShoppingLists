package com.machmudow.shoppinglists.infrastructure.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.machmudow.shoppinglists.infrastructure.model.ShoppingItem

class Converters {

    @TypeConverter
    fun toShoppingItems(value: String?): List<ShoppingItem>? {
        val type = object : TypeToken<List<ShoppingItem>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromShoppingItems(shoppingItems: List<ShoppingItem>?): String? {
        val type = object : TypeToken<List<ShoppingItem>>() {}.type
        return Gson().toJson(shoppingItems, type)
    }
}