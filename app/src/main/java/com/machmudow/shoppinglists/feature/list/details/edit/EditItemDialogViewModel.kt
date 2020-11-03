package com.machmudow.shoppinglists.feature.list.details.edit

import com.machmudow.shoppinglists.infrastructure.model.ShoppingItem
import com.machmudow.shoppinglists.utils.BaseViewModel
import javax.inject.Inject

class EditItemDialogViewModel
@Inject constructor(
    private val repository: EditItemDialogRepository
) : BaseViewModel() {

    val status = repository.status

    private val updateProcessor = repository.updateProcessor

    fun updateShoppingItem(shoppingItem: ShoppingItem) {
        if(!updateProcessor.hasSubscribers())
            compositeDisposable.add(repository.updateShoppingItem(shoppingItem))
        else
            updateProcessor.onNext(shoppingItem)
    }
}