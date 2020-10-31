package com.machmudow.shoppinglists.feature.list.current

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.FragmentShoppingListBinding
import com.machmudow.shoppinglists.feature.list.ShoppingListAdapter
import com.machmudow.shoppinglists.feature.list.current.create.NewListDaggerDialogFragment
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import com.machmudow.shoppinglists.infrastructure.room.ShoppingItemDAO
import com.machmudow.shoppinglists.infrastructure.room.ShoppingListDAO
import com.machmudow.shoppinglists.utils.BaseDaggerFragment
import javax.inject.Inject

class CurrentListFragment : BaseDaggerFragment(R.layout.fragment_shopping_list) {

    @Inject
    lateinit var shoppingListDAO: ShoppingListDAO

    @Inject
    lateinit var shoppingItemDAO: ShoppingItemDAO

    @Inject
    lateinit var viewModel: CurrentListViewModel

    private val binding get() = _binding as FragmentShoppingListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShoppingListBinding.bind(view)

        viewModel = ViewModelProvider(this, viewModelFactory).get(CurrentListViewModel::class.java)

        initRecyclerView()
        onFabClick()
    }

    private fun initRecyclerView() {
        val mLayoutManager = LinearLayoutManager(requireContext())
        val mAdapter = ShoppingListAdapter()

        binding.recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }
        getShoppingLists(mAdapter)
    }

    private fun onFabClick() {
        val newListFragment = NewListDaggerDialogFragment.newInstance()
        binding.fab.setOnClickListener {
            if (!newListFragment.isAdded)
                newListFragment.show(childFragmentManager, newListFragment.tag)
        }
    }

    private fun getShoppingLists(adapter: ShoppingListAdapter) {
        viewModel.shoppingLists.observe(viewLifecycleOwner) { shoppingLists ->
            adapter.setRecyclerList(shoppingLists)
        }
    }
}