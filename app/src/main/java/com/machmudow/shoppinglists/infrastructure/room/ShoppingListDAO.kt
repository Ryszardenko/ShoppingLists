package com.machmudow.shoppinglists.infrastructure.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import com.machmudow.shoppinglists.infrastructure.model.ShoppingListWithItems

@Dao
interface ShoppingListDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoppingList(shoppingList: ShoppingList)

    @Query("DELETE FROM ShoppingList")
    suspend fun dropTable()

    @Update
    fun update(shoppingList: ShoppingList)

    @Query("DELETE FROM ShoppingList WHERE id = :shoppingListId")
    suspend fun delete(shoppingListId: Int)

    @Query("UPDATE ShoppingList SET isArchived = 1 WHERE id = :shoppingListId")
    suspend fun archive(shoppingListId: Int)

    @Query("UPDATE ShoppingList SET isArchived = 0 WHERE id = :shoppingListId")
    suspend fun unarchive(shoppingListId: Int)

    @Transaction
    @Query("SELECT * FROM ShoppingList WHERE id = :shoppingListId")
    fun getShoppingListWithItems(shoppingListId: Int): LiveData<ShoppingListWithItems>

    @Transaction
    @Query("SELECT * FROM ShoppingList WHERE isArchived = 0")
    fun getShoppingListsWithItems(): LiveData<List<ShoppingListWithItems>>

    @Transaction
    @Query("SELECT * FROM ShoppingList WHERE isArchived = 1")
    fun getArchivedShoppingListsWithItems(): LiveData<List<ShoppingListWithItems>>
}