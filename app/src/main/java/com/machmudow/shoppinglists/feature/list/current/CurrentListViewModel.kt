package com.machmudow.shoppinglists.feature.list.current

import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import com.machmudow.shoppinglists.utils.BaseViewModel
import com.machmudow.shoppinglists.utils.Log
import javax.inject.Inject

class CurrentListViewModel
@Inject constructor(
    private val repository: CurrentListRepository
) : BaseViewModel() {

    val shoppingLists = repository.shoppingLists

    fun removeShoppingList(shoppingList: ShoppingList) {
        repository.removeShoppingList(shoppingList)
    }
}