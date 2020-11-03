package com.machmudow.shoppinglists.feature.list.details.create

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.FragmentNewItemBinding
import com.machmudow.shoppinglists.feature.list.details.BaseItemDialogFragment
import com.machmudow.shoppinglists.infrastructure.model.ShoppingItem
import com.machmudow.shoppinglists.utils.*
import com.machmudow.shoppinglists.utils.Constants.SHOPPING_LIST_ID
import com.machmudow.shoppinglists.utils.ToastUtils.showShortToast
import javax.inject.Inject

class NewItemDialogFragment : BaseItemDialogFragment() {

    companion object {
        fun newInstance(shoppingListId: Int): NewItemDialogFragment {
            val args = Bundle()
            args.putInt(SHOPPING_LIST_ID, shoppingListId)
            val fragment = NewItemDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var viewModel: NewItemDialogViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(NewItemDialogViewModel::class.java)

        onConfirmClick()
        observeStatus(viewModel.status)
    }

    private fun onConfirmClick() {
        val shoppingListId = arguments?.getInt(SHOPPING_LIST_ID)

        shoppingListId?.let {
            onConfirmClick {
                val shoppingItem = createShoppingItem(shoppingListId)
                viewModel.insertShoppingItem(shoppingItem)
            }
        }
    }

    private fun createShoppingItem(shoppingListId: Int): ShoppingItem {
        return ShoppingItem(
            shoppingListId = shoppingListId,
            title = binding.etTitle.text.toString(),
            quantity = binding.etQuantity.text.toString().toInt(),
        )
    }
}