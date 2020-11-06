package com.machmudow.shoppinglists.feature.list.details.create

import com.machmudow.shoppinglists.infrastructure.model.ShoppingItem
import com.machmudow.shoppinglists.base.BaseViewModel
import javax.inject.Inject

class NewItemDialogViewModel
@Inject constructor(
    private val repository: NewItemDialogRepository
) : BaseViewModel() {

    val status = repository.status

    private val insertProcessor = repository.insertProcessor

    fun insertShoppingItem(shoppingItem: ShoppingItem) {
        if(!insertProcessor.hasSubscribers())
            compositeDisposable.add(repository.insertShoppingItem(shoppingItem))
        else
            insertProcessor.onNext(shoppingItem)
    }
}