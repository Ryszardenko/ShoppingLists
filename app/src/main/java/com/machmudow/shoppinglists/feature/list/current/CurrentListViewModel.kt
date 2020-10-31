package com.machmudow.shoppinglists.feature.list.current

import com.machmudow.shoppinglists.utils.BaseViewModel
import javax.inject.Inject

class CurrentListViewModel
@Inject constructor(
    private val repository: CurrentListRepository
) : BaseViewModel() {

    val shoppingLists = repository.shoppingLists
}