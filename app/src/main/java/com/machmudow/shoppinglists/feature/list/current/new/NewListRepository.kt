package com.machmudow.shoppinglists.feature.list.current.new

import com.machmudow.shoppinglists.infrastructure.room.ShoppingListDAO
import javax.inject.Inject

class NewListRepository
@Inject constructor(
    private val shoppingListDAO: ShoppingListDAO
) {

    fun createShoppingList() {}
}