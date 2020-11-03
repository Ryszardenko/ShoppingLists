package com.machmudow.shoppinglists.infrastructure.room

import androidx.room.*
import com.machmudow.shoppinglists.infrastructure.model.ShoppingItem

@Dao
interface ShoppingItemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoppingItem(shoppingItem: ShoppingItem)

    @Query("DELETE FROM ShoppingItem")
    fun dropTable()

    @Update
    fun update(shoppingItem: ShoppingItem)

    @Query("UPDATE ShoppingItem SET isInCart = 1 WHERE id = :shoppingItemId")
    fun addToCart(shoppingItemId: Int)

    @Query("UPDATE ShoppingItem SET isInCart = 0 WHERE id = :shoppingItemId")
    fun removeFromCart(shoppingItemId: Int)

    @Query("DELETE FROM ShoppingItem WHERE id = :shoppingItemId")
    fun deleteItem(shoppingItemId: Int)
}