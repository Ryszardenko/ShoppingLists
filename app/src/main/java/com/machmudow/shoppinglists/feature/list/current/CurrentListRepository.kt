package com.machmudow.shoppinglists.feature.list.current

import com.machmudow.shoppinglists.infrastructure.room.ShoppingListDAO
import javax.inject.Inject

class CurrentListRepository
@Inject constructor(
    private val shoppingListDAO: ShoppingListDAO
) {

    val shoppingLists = shoppingListDAO.getShoppingListsWithItems()

    suspend fun deleteShoppingList(shoppingListId: Int) = shoppingListDAO.delete(shoppingListId)

    suspend fun archiveShoppingList(shoppingListId: Int) = shoppingListDAO.archive(shoppingListId)
}