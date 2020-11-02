package com.machmudow.shoppinglists.feature.list.details

import com.machmudow.shoppinglists.utils.BaseViewModel
import javax.inject.Inject

class DetailsFragmentViewModel
@Inject constructor(
    private val repository: DetailsFragmentRepository
) : BaseViewModel() {

    fun getShoppingListWithItems(shoppingListId: Int) = repository.getShoppingListWithItems(shoppingListId)

//    fun addToCart() = repository.addToCart
}