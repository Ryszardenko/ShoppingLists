package com.machmudow.shoppinglists.infrastructure.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.machmudow.shoppinglists.infrastructure.model.ShoppingItem
import io.reactivex.Flowable

@Dao
interface ShoppingItemDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoppingItem(shoppingItem: ShoppingItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoppingItems(shoppingItems: List<ShoppingItem>)

    @Query("DELETE FROM ShoppingItem")
    fun dropTable()

    @Update
    fun update(shoppingItem: ShoppingItem)

    @Query("SELECT * FROM ShoppingItem")
    fun getShoppingItems(): List<ShoppingItem>

    @Query("UPDATE ShoppingItem SET isInCart = 1 WHERE id = :shoppingItemId")
    fun addToCart(shoppingItemId: Int)

    @Query("UPDATE ShoppingItem SET isInCart = 0 WHERE id = :shoppingItemId")
    fun removeFromCart(shoppingItemId: Int)
}