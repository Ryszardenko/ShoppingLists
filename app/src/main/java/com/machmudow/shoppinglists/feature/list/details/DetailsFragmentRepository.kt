package com.machmudow.shoppinglists.feature.list.details

import com.machmudow.shoppinglists.infrastructure.room.ShoppingItemDAO
import com.machmudow.shoppinglists.infrastructure.room.ShoppingListDAO
import javax.inject.Inject

class DetailsFragmentRepository
@Inject constructor(
    private val shoppingListDAO: ShoppingListDAO,
    private val shoppingItemDAO: ShoppingItemDAO
) {
    fun getShoppingListWithItems(shoppingListId: Int) = shoppingListDAO.getShoppingListWithItems(shoppingListId)

    suspend fun addToCart(shoppingItemId: Int) = shoppingItemDAO.addToCart(shoppingItemId)

    suspend fun removeFromCart(shoppingItemId: Int) = shoppingItemDAO.removeFromCart(shoppingItemId)

    suspend fun deleteItem(shoppingItemId: Int) = shoppingItemDAO.deleteItem(shoppingItemId)
}