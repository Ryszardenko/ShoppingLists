package com.machmudow.shoppinglists.feature.list.current.edit

import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import com.machmudow.shoppinglists.utils.BaseViewModel
import javax.inject.Inject

class EditListDialogViewModel
@Inject constructor(
        private val repository: EditListDialogRepository
) : BaseViewModel() {

    val status = repository.status

    fun editShoppingList(shoppingList: ShoppingList) {
        compositeDisposable.add(repository.editShoppingList(shoppingList))
    }
}