package com.machmudow.shoppinglists.feature.list.current.create

import androidx.lifecycle.MutableLiveData
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import com.machmudow.shoppinglists.infrastructure.room.ShoppingListDAO
import com.machmudow.shoppinglists.utils.Log
import com.machmudow.shoppinglists.utils.Status
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewListDialogRepository
@Inject constructor(
    private val shoppingListDAO: ShoppingListDAO
) {
    val status = MutableLiveData<Status>()

    fun createShoppingList(shoppingList: ShoppingList): Disposable {
        return Single.just(shoppingList)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe {
                shoppingListDAO.insertShoppingList(shoppingList)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                status.value = Status.SUCCESS
            }, {
                status.value = Status.ERROR
                Log.d("NewListRepository createShoppingList() Error ${it.message}")
            })
    }
}