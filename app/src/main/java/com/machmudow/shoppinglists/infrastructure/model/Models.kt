package com.machmudow.shoppinglists.infrastructure.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.machmudow.shoppinglists.utils.DateUtils
import java.io.Serializable

@Entity
data class ShoppingList(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val date: String = DateUtils.getCurrentYearMonthDayTime(),
    val shoppingItems: List<ShoppingItem>? = null,
    val isArchived: Boolean = false
) : Serializable

@Entity
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val itemType: ItemType,
    val title: String,
    val quantity: Int,
    var isInCart: Boolean = false
)

enum class ItemType {
    food,
    drinks,
    clothes,
    shoes,
    electronics,
    other
}