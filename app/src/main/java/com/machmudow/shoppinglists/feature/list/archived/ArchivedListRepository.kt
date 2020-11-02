package com.machmudow.shoppinglists.feature.list.archived

import com.machmudow.shoppinglists.infrastructure.room.ShoppingListDAO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArchivedListRepository
@Inject constructor(
   private val shoppingListDAO: ShoppingListDAO
) {

    val archivedLists = shoppingListDAO.getArchivedShoppingLists()

    fun unarchive(shoppingListId: Int) =
        GlobalScope.launch {
            shoppingListDAO.unarchive(shoppingListId)
        }
}