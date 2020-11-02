package com.machmudow.shoppinglists.feature.list.current

import com.machmudow.shoppinglists.infrastructure.room.ShoppingListDAO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentListRepository
@Inject constructor(
    private val shoppingListDAO: ShoppingListDAO
) {

    val shoppingLists = shoppingListDAO.getShoppingListsWithItems()

    fun deleteShoppingList(shoppingListId: Int) =
        GlobalScope.launch {
            shoppingListDAO.delete(shoppingListId)
        }

    fun archiveShoppingList(shoppingListId: Int) =
        GlobalScope.launch {
            shoppingListDAO.archive(shoppingListId)
        }
}