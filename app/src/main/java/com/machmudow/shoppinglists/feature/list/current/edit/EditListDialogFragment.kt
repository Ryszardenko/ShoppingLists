package com.machmudow.shoppinglists.feature.list.current.edit

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.feature.list.current.BaseListDialogFragment
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import com.machmudow.shoppinglists.utils.Constants.SHOPPING_LIST
import javax.inject.Inject

class EditListDialogFragment : BaseListDialogFragment() {

    companion object {
        fun newInstance(shoppingList: ShoppingList): EditListDialogFragment {
            val args = Bundle()
            args.putSerializable(SHOPPING_LIST, shoppingList)
            val fragment = EditListDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var viewModel: EditListDialogViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(EditListDialogViewModel::class.java)

        val shoppingList = arguments?.getSerializable(SHOPPING_LIST) as ShoppingList

        setDialogTitle(shoppingList)
        onConfirmClick {
            val editedShoppingList = shoppingList.copy(
                title = binding.etTitle.text.toString()
            )
            viewModel.editShoppingList(editedShoppingList)
        }
        observeStatus(viewModel.status)
    }

    private fun setDialogTitle(shoppingList: ShoppingList) {
        binding.tvDialogTitle.setText(R.string.edit_a_shopping_list)
        binding.etTitle.setText(shoppingList.title)
    }
}