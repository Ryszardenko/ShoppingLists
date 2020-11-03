package com.machmudow.shoppinglists.feature.list.current.create

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.machmudow.shoppinglists.feature.list.current.BaseListDialogFragment
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import javax.inject.Inject

class NewListDialogFragment : BaseListDialogFragment() {

    companion object {
        fun newInstance() = NewListDialogFragment()
    }

    @Inject
    lateinit var viewModel: NewListDialogViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(NewListDialogViewModel::class.java)

        onConfirmClick {
            val shoppingList = createShoppingList()
            viewModel.createShoppingList(shoppingList)
        }

        observeStatus(viewModel.status)
    }

    private fun createShoppingList(): ShoppingList {
        val title = binding.etTitle.text.toString()

        return ShoppingList(title = title)
    }
}