package com.machmudow.shoppinglists.feature.list.current.create

import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import com.machmudow.shoppinglists.utils.BaseViewModel
import com.machmudow.shoppinglists.utils.Log
import javax.inject.Inject

class NewListViewModel
@Inject constructor(
    private val repository: NewListRepository
) : BaseViewModel() {

    fun createShoppingList(shoppingList: ShoppingList) {
        repository.createShoppingList(shoppingList)
    }
}