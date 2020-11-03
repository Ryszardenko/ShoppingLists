package com.machmudow.shoppinglists.feature.list.details

import com.machmudow.shoppinglists.infrastructure.room.ShoppingItemDAO
import com.machmudow.shoppinglists.infrastructure.room.ShoppingListDAO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsFragmentRepository
@Inject constructor(
    private val shoppingListDAO: ShoppingListDAO,
    private val shoppingItemDAO: ShoppingItemDAO
) {
    fun getShoppingListWithItems(shoppingListId: Int) = shoppingListDAO.getShoppingListWithItems(shoppingListId)

    fun addToCart(shoppingItemId: Int) =
        GlobalScope.launch {
            shoppingItemDAO.addToCart(shoppingItemId)
        }

    fun removeFromCart(shoppingItemId: Int) =
        GlobalScope.launch {
            shoppingItemDAO.removeFromCart(shoppingItemId)
        }

    fun deleteItem(shoppingItemId: Int) =
        GlobalScope.launch {
            shoppingItemDAO.deleteItem(shoppingItemId)
        }
}