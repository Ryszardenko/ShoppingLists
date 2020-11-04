package com.machmudow.shoppinglists.feature.list.details

import androidx.lifecycle.viewModelScope
import com.machmudow.shoppinglists.utils.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsFragmentViewModel
@Inject constructor(
    private val repository: DetailsFragmentRepository
) : BaseViewModel() {

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