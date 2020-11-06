package com.machmudow.shoppinglists.feature.list.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentListViewModel
@Inject constructor(
    private val repository: CurrentListRepository
) : ViewModel() {

    val shoppingLists = repository.shoppingLists

    fun archiveShoppingList(shoppingListId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.archiveShoppingList(shoppingListId)
        }
    }

    fun deleteShoppingList(shoppingListId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteShoppingList(shoppingListId)
        }
    }
}