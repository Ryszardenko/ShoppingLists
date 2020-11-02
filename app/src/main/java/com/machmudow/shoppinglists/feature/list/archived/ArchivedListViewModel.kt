package com.machmudow.shoppinglists.feature.list.archived

import com.machmudow.shoppinglists.utils.BaseViewModel
import javax.inject.Inject

class ArchivedListViewModel
@Inject constructor(
    private val repository: ArchivedListRepository
) : BaseViewModel() {

    val archivedLists = repository.archivedLists

    fun unarchive(shoppingListId: Int) = repository.unarchive(shoppingListId)
    fun delete(shoppingListId: Int) = repository.delete(shoppingListId)
}