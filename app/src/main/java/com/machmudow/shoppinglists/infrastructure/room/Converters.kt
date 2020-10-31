package com.machmudow.shoppinglists.infrastructure.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.machmudow.shoppinglists.infrastructure.model.ShoppingItem
import com.machmudow.shoppinglists.infrastructure.model.ItemType

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

    @TypeConverter
    fun toItemType(value: String): ItemType {
        val type = object : TypeToken<ItemType>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromItemType(itemType: ItemType): String {
        val type = object : TypeToken<ItemType>() {}.type
        return Gson().toJson(itemType, type)
    }
}