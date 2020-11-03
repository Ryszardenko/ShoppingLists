package com.machmudow.shoppinglists.feature.list.details.edit

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

class EditItemDialogRepository
@Inject constructor(
    private val shoppingItemDAO: ShoppingItemDAO
) {
    val status = MutableLiveData<Status>()

    val updateProcessor = PublishProcessor.create<ShoppingItem>()

    fun updateShoppingItem(shoppingItem: ShoppingItem): Disposable {
        return updateProcessor
            .startWith(shoppingItem)
            .doOnNext {
                shoppingItemDAO.update(shoppingItem)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                status.value = Status.SUCCESS
            }, {
                status.value = Status.ERROR
                Log.d("EditItemDialogRepository updateShoppingItem() Error ${it.message}")
            })
    }
}