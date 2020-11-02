package com.machmudow.shoppinglists.feature.list.current

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.FragmentListBinding
import com.machmudow.shoppinglists.feature.list.ShoppingListAdapter
import com.machmudow.shoppinglists.feature.list.ShoppingListListener
import com.machmudow.shoppinglists.feature.list.current.create.NewListDialogFragment
import com.machmudow.shoppinglists.feature.list.current.edit.EditListDialogFragment
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import com.machmudow.shoppinglists.utils.BaseDaggerFragment
import javax.inject.Inject

class CurrentListFragment : BaseDaggerFragment(R.layout.fragment_list),
    ShoppingListListener {

    @Inject
    lateinit var viewModel: CurrentListViewModel

    private val binding get() = _binding as FragmentListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CurrentListViewModel::class.java)

        initRecyclerView()
        onFabClick()
    }

    override fun showDialog(shoppingList: ShoppingList) {
        AlertDialog.Builder(requireContext())
            .setItems(R.array.edit) { _, which ->
                when (which) {
                    0 -> editShoppingList(shoppingList)
                    1 -> viewModel.removeShoppingList(shoppingList)
                }
            }
            .show()
    }

    private fun initRecyclerView() {
        val mLayoutManager = LinearLayoutManager(requireContext())
        val mAdapter = ShoppingListAdapter(this)

        binding.recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }
        getShoppingLists(mAdapter)
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

    private fun getShoppingLists(adapter: ShoppingListAdapter) {
        viewModel.shoppingLists.observe(viewLifecycleOwner) { shoppingLists ->
            adapter.setRecyclerList(shoppingLists)
            setEmptyTvVisibility(shoppingLists)
        }
    }

    private fun setEmptyTvVisibility(shoppingLists: List<ShoppingList>) {
        binding.tvEmpty.visibility =
            if (shoppingLists.isEmpty())
                View.VISIBLE
            else
                View.GONE
    }
}