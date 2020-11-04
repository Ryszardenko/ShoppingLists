package com.machmudow.shoppinglists.feature.list.archived

import com.machmudow.shoppinglists.infrastructure.room.ShoppingListDAO
import javax.inject.Inject

class ArchivedListRepository
@Inject constructor(
    private val shoppingListDAO: ShoppingListDAO
) {

    val archivedLists = shoppingListDAO.getArchivedShoppingListsWithItems()

    suspend fun unarchive(shoppingListId: Int) = shoppingListDAO.unarchive(shoppingListId)

    suspend fun delete(shoppingListId: Int) = shoppingListDAO.delete(shoppingListId)
}