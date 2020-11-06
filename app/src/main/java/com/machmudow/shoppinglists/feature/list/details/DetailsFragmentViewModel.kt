package com.machmudow.shoppinglists.feature.list.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsFragmentViewModel
@Inject constructor(
    private val repository: DetailsFragmentRepository
) : ViewModel() {

    fun getShoppingListWithItems(shoppingListId: Int) =
        repository.getShoppingListWithItems(shoppingListId)

    fun addToCart(shoppingItemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addToCart(shoppingItemId)
        }
    }

    fun removeFromCart(shoppingItemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFromCart(shoppingItemId)
        }
    }

    fun deleteItem(shoppingItemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(shoppingItemId)
        }
    }
}