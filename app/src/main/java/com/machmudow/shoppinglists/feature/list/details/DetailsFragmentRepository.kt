package com.machmudow.shoppinglists.feature.list.details

import com.machmudow.shoppinglists.infrastructure.room.ShoppingListDAO
import javax.inject.Inject

class DetailsFragmentRepository
@Inject constructor(
    private val shoppingListDAO: ShoppingListDAO
) {
    fun getShoppingListWithItems(shoppingListId: Int) = shoppingListDAO.getShoppingListWithItems(shoppingListId)
}