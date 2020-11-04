package com.machmudow.shoppinglists.feature.list.current

import androidx.lifecycle.viewModelScope
import com.machmudow.shoppinglists.utils.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentListViewModel
@Inject constructor(
    private val repository: CurrentListRepository
) : BaseViewModel() {

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