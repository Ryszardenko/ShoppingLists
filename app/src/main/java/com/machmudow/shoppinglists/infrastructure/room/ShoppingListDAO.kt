package com.machmudow.shoppinglists.infrastructure.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList

@Dao
interface ShoppingListDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoppingList(shoppingList: ShoppingList)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShoppingLists(shoppingLists: List<ShoppingList>)

    @Query("DELETE FROM ShoppingList")
    fun dropTable()

    @Update
    fun update(shoppingList: ShoppingList)

    @Query("SELECT * FROM ShoppingList WHERE isArchived = 0")
    fun getShoppingLists(): LiveData<List<ShoppingList>>

    @Query("SELECT * FROM ShoppingList WHERE isArchived = 1")
    fun getArchivedShoppingLists(): LiveData<List<ShoppingList>>
}