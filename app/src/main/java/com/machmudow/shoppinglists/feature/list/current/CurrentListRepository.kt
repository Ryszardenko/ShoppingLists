package com.machmudow.shoppinglists.feature.list.current

import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import com.machmudow.shoppinglists.infrastructure.room.ShoppingListDAO
import com.machmudow.shoppinglists.utils.Log
import io.reactivex.Completable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentListRepository
@Inject constructor(
    private val shoppingListDAO: ShoppingListDAO
) {

    val shoppingLists = shoppingListDAO.getShoppingLists()

    fun removeShoppingList(shoppingList: ShoppingList) =
        GlobalScope.launch {
            shoppingListDAO.remove(shoppingList.id)
        }
}