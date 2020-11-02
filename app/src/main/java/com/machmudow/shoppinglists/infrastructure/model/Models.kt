package com.machmudow.shoppinglists.infrastructure.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.machmudow.shoppinglists.utils.DateUtils
import java.io.Serializable

@Entity
data class ShoppingList(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val date: String = DateUtils.getCurrentYearMonthDayTime(),
//    val shoppingItems: List<ShoppingItem> = listOf(),
    val isArchived: Boolean = false
) : Serializable

@Entity
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val shoppingListId: Int,
    val title: String,
    val quantity: Int,
    var isInCart: Boolean = false
)

data class ShoppingListWithItems(
    @Embedded val shoppingList: ShoppingList,
    @Relation(
        parentColumn = "id",
        entityColumn = "shoppingListId"
    )
    val shoppingItems: List<ShoppingItem>
)