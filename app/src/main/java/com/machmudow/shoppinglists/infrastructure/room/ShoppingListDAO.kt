package com.machmudow.shoppinglists.infrastructure.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import io.reactivex.Completable
import io.reactivex.Flowable

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

    @Query("DELETE FROM ShoppingList WHERE id = :shoppingListId")
    fun remove(shoppingListId: Int)

    @Query("UPDATE ShoppingList SET isArchived = 1 WHERE id = :shoppingListId")
    fun archive(shoppingListId: Int)

    @Query("UPDATE ShoppingList SET isArchived = 0 WHERE id = :shoppingListId")
    fun unarchive(shoppingListId: Int)

    @Query("SELECT * FROM ShoppingList WHERE isArchived = 0 ORDER BY date DESC")
    fun getShoppingLists(): LiveData<List<ShoppingList>>

    @Query("SELECT * FROM ShoppingList WHERE isArchived = 1 ORDER BY date DESC")
    fun getArchivedShoppingLists(): LiveData<List<ShoppingList>>
}