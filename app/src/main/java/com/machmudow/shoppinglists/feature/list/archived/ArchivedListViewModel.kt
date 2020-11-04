package com.machmudow.shoppinglists.feature.list.archived

import androidx.lifecycle.viewModelScope
import com.machmudow.shoppinglists.utils.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArchivedListViewModel
@Inject constructor(
    private val repository: ArchivedListRepository
) : BaseViewModel() {

    val archivedLists = repository.archivedLists

    fun unarchive(shoppingListId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.unarchive(shoppingListId)
        }
    }

    fun delete(shoppingListId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(shoppingListId)
        }
    }
}