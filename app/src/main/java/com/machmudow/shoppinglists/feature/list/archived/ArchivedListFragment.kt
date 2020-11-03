package com.machmudow.shoppinglists.feature.list.archived

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.feature.list.BaseListFragment
import com.machmudow.shoppinglists.feature.list.current.ShoppingListListener
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import javax.inject.Inject

class ArchivedListFragment : BaseListFragment(), ShoppingListListener {

    companion object {
        fun newInstance() = ArchivedListFragment()
    }

    @Inject
    lateinit var viewModel: ArchivedListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(ArchivedListViewModel::class.java)

        initRecyclerView(this, viewModel.archivedLists)
        setEmptyTvText()
        hideFab()
    }

    override fun showDialog(shoppingList: ShoppingList) {
        AlertDialog.Builder(requireContext())
            .setItems(R.array.list_archived_edit) { _, which ->
                when (which) {
                    0 -> viewModel.unarchive(shoppingList.id)
                    1 -> viewModel.delete(shoppingList.id)
                }
            }
            .show()
    }

    private fun setEmptyTvText() {
        binding.tvEmpty.text = getString(R.string.no_archived_shopping_list)
    }

    private fun hideFab() {
        binding.fab.visibility = View.GONE
    }
}