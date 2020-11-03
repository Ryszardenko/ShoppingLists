package com.machmudow.shoppinglists.infrastructure.model

import androidx.room.*
import com.machmudow.shoppinglists.utils.DateUtils
import java.io.Serializable

@Entity
data class ShoppingList(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val date: String = DateUtils.getCurrentYearMonthDayTime(),
    val isArchived: Boolean = false
) : Serializable

@Entity(foreignKeys = [ForeignKey(entity = ShoppingList::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("shoppingListId"),
    onDelete = ForeignKey.CASCADE)]
)
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val shoppingListId: Int,
    val title: String,
    val quantity: Int,
    var isInCart: Boolean = false
) : Serializable

data class ShoppingListWithItems(
    @Embedded val shoppingList: ShoppingList,
    @Relation(
        parentColumn = "id",
        entityColumn = "shoppingListId"
    )
    val shoppingItems: List<ShoppingItem>
)