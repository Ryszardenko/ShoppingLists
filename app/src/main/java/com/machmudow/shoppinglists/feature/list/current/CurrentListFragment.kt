package com.machmudow.shoppinglists.feature.list.current

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.feature.list.BaseListFragment
import com.machmudow.shoppinglists.feature.list.current.create.NewListDialogFragment
import com.machmudow.shoppinglists.feature.list.current.edit.EditListDialogFragment
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import javax.inject.Inject

class CurrentListFragment : BaseListFragment(), ShoppingListListener {

    @Inject
    lateinit var viewModel: CurrentListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CurrentListViewModel::class.java)

        initRecyclerView(this, viewModel.shoppingLists)
        onFabClick()
    }

    override fun showDialog(shoppingList: ShoppingList) {
        AlertDialog.Builder(requireContext())
            .setItems(R.array.edit) { _, which ->
                when (which) {
                    0 -> editShoppingList(shoppingList)
                    1 -> viewModel.archiveShoppingList(shoppingList.id)
                    2 -> viewModel.removeShoppingList(shoppingList.id)
                }
            }
            .show()
    }

    private fun editShoppingList(shoppingList: ShoppingList) {
        val editShoppingListDialogFragment = EditListDialogFragment.newInstance(shoppingList)
        if (!editShoppingListDialogFragment.isAdded)
            editShoppingListDialogFragment.show(
                childFragmentManager,
                editShoppingListDialogFragment.tag
            )
    }

    private fun onFabClick() {
        val newListFragment = NewListDialogFragment.newInstance()
        binding.fab.setOnClickListener {
            if (!newListFragment.isAdded)
                newListFragment.show(childFragmentManager, newListFragment.tag)
        }
    }
}