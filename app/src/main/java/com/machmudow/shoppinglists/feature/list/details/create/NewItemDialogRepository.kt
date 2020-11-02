package com.machmudow.shoppinglists.feature.list.details.create

import androidx.lifecycle.MutableLiveData
import com.machmudow.shoppinglists.infrastructure.model.ShoppingItem
import com.machmudow.shoppinglists.infrastructure.room.ShoppingItemDAO
import com.machmudow.shoppinglists.utils.Log
import com.machmudow.shoppinglists.utils.Status
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewItemDialogRepository
@Inject constructor(
    private val shoppingItemDAO: ShoppingItemDAO
) {
    val status = MutableLiveData<Status>()

    val insertProcessor = PublishProcessor.create<ShoppingItem>()

    fun insertShoppingItem(shoppingItem: ShoppingItem): Disposable {
        return insertProcessor
            .startWith(shoppingItem)
            .doOnNext {
                shoppingItemDAO.insertShoppingItem(shoppingItem)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                status.value = Status.SUCCESS
            }, {
                status.value = Status.ERROR
                Log.d("NewItemDialogRepository insertShoppingItem() Error ${it.message}")
            })
    }
}