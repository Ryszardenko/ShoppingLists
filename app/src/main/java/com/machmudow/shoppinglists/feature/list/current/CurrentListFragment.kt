package com.machmudow.shoppinglists.feature.list.current

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.FragmentShoppingListBinding
import com.machmudow.shoppinglists.feature.list.ShoppingListAdapter
import com.machmudow.shoppinglists.feature.list.current.new.NewListFragment
import com.machmudow.shoppinglists.infrastructure.model.ShoppingItem
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import com.machmudow.shoppinglists.infrastructure.model.ItemType
import com.machmudow.shoppinglists.infrastructure.room.ShoppingItemDAO
import com.machmudow.shoppinglists.infrastructure.room.ShoppingListDAO
import com.machmudow.shoppinglists.utils.BaseDaggerFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentListFragment : BaseDaggerFragment(R.layout.fragment_shopping_list) {

    @Inject
    lateinit var shoppingListDAO: ShoppingListDAO

    @Inject
    lateinit var shoppingItemDAO: ShoppingItemDAO

    private val binding get() = _binding as FragmentShoppingListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentShoppingListBinding.bind(view)

        initRecyclerView()
        onFabClick()
    }

    private fun initRecyclerView() {
        val shoppingItem = ShoppingItem(
            0, ItemType.clothes, "test", 5
        )
        val list = listOf(
            ShoppingList(title = "pierwsza", description = "pierwsza")
        )

        val mLayoutManager = LinearLayoutManager(requireContext())
        val mAdapter = ShoppingListAdapter()

        binding.recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }
        mAdapter.setRecyclerList(list)

        GlobalScope.launch {
            shoppingItemDAO.dropTable()
        }
    }

    private fun onFabClick() {
        val newListFragment = NewListFragment.newInstance()
        binding.fab.setOnClickListener {
            if (!newListFragment.isAdded)
                newListFragment.show(childFragmentManager, newListFragment.tag)
        }
    }
}