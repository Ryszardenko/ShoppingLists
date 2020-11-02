package com.machmudow.shoppinglists.feature.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.FragmentListBinding
import com.machmudow.shoppinglists.feature.list.current.ListAdapter
import com.machmudow.shoppinglists.feature.list.current.ShoppingListListener
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import com.machmudow.shoppinglists.utils.BaseDaggerFragment

abstract class BaseListFragment : BaseDaggerFragment(R.layout.fragment_list) {

    val binding get() = _binding as FragmentListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)
    }

    fun initRecyclerView(listener: ShoppingListListener, list: LiveData<List<ShoppingList>>) {
        val mLayoutManager = LinearLayoutManager(requireContext())
        val mAdapter = ListAdapter(listener)

        binding.recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }
        getShoppingLists(mAdapter, list)
    }

    fun setEmptyTvVisibility(shoppingLists: List<ShoppingList>) {
        binding.tvEmpty.visibility =
            if (shoppingLists.isEmpty())
                View.VISIBLE
            else
                View.GONE
    }

    private fun getShoppingLists(adapter: ListAdapter, list: LiveData<List<ShoppingList>>) {
        list.observe(viewLifecycleOwner) { archivedLists ->
            adapter.setRecyclerList(archivedLists)
            setEmptyTvVisibility(archivedLists)
        }
    }
}