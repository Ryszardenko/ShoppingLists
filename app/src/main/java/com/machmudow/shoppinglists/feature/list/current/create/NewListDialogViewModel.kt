package com.machmudow.shoppinglists.feature.list.current.create

import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import com.machmudow.shoppinglists.base.BaseViewModel
import javax.inject.Inject

class NewListDialogViewModel
@Inject constructor(
    private val repository: NewListDialogRepository
) : BaseViewModel() {

    val status = repository.status

    fun createShoppingList(shoppingList: ShoppingList) {
        compositeDisposable.add(
            repository.createShoppingList(shoppingList)
        )
    }
}