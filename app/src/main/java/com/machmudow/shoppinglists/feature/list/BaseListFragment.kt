package com.machmudow.shoppinglists.feature.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.FragmentListBinding
import com.machmudow.shoppinglists.feature.list.current.ListAdapter
import com.machmudow.shoppinglists.feature.list.current.ShoppingListListener
import com.machmudow.shoppinglists.feature.list.details.DetailsFragment
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import com.machmudow.shoppinglists.utils.BaseDaggerFragment

abstract class BaseListFragment : BaseDaggerFragment(R.layout.fragment_list), ShoppingListListener {

    val binding get() = _binding as FragmentListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentListBinding.bind(view)
    }

    override fun navigateTo(shoppingListId: Int) {
        val navOptions: NavOptions = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setPopUpTo(R.id.detailsFragment, true)
            .build()

        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        navController.navigate(R.id.detailsFragment, DetailsFragment.setArguments(shoppingListId), navOptions)
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

    private fun getShoppingLists(adapter: ListAdapter, list: LiveData<List<ShoppingList>>) {
        list.observe(viewLifecycleOwner) { archivedLists ->
            adapter.setRecyclerList(archivedLists)
            setEmptyTvVisibility(archivedLists)
            hideProgressLayout()
        }
    }

    private fun setEmptyTvVisibility(shoppingLists: List<ShoppingList>) {
        binding.tvEmpty.visibility =
            if (shoppingLists.isEmpty())
                View.VISIBLE
            else
                View.GONE
    }

    private fun hideProgressLayout() {
        binding.layoutProgress.root.visibility = View.GONE
    }
}