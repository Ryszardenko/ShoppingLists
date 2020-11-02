package com.machmudow.shoppinglists.feature.list.details

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.FragmentDetailsBinding
import com.machmudow.shoppinglists.feature.list.details.create.NewItemDialogFragment
import com.machmudow.shoppinglists.infrastructure.model.ShoppingItem
import com.machmudow.shoppinglists.utils.BaseDaggerFragment
import com.machmudow.shoppinglists.utils.Constants.SHOPPING_LIST_ID
import javax.inject.Inject


class DetailsFragment : BaseDaggerFragment(R.layout.fragment_details), DetailsListener {

    companion object {
        fun setArguments(shoppingListId: Int): Bundle {
            val args = Bundle()
            args.putInt(SHOPPING_LIST_ID, shoppingListId)
            return args
        }
    }

    @Inject
    lateinit var viewModel: DetailsFragmentViewModel

    val binding get() = _binding as FragmentDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)

        val shoppingListId = arguments?.getInt(SHOPPING_LIST_ID)

        shoppingListId?.let {
            initRecyclerView(shoppingListId)
            onFabClick(shoppingListId)
        }
    }

    override fun addToCart() {
    }

    override fun removeFromCart() {
    }

    private fun initRecyclerView(shoppingListId: Int) {
        val mLayoutManager = LinearLayoutManager(requireContext())
        val mAdapter = DetailsAdapter(this)

        binding.recyclerView.apply {
            layoutManager = mLayoutManager
            adapter = mAdapter
        }
        getShoppingList(shoppingListId, mAdapter)
    }

    private fun getShoppingList(shoppingListId: Int, adapter: DetailsAdapter) {
        viewModel.getShoppingListWithItems(shoppingListId)
            .observe(viewLifecycleOwner) { shoppingListWithItems ->
                initToolbar(shoppingListWithItems.shoppingList.title)
                adapter.setRecyclerList(shoppingListWithItems.shoppingItems)
                setEmptyTvVisibility(shoppingListWithItems.shoppingItems)
                hideProgressBar()
            }
    }

    private fun onFabClick(shoppingListId: Int) {
        val newItemDialogFragment = NewItemDialogFragment.newInstance(shoppingListId)
        binding.fab.setOnClickListener {
            if (!newItemDialogFragment.isAdded)
                newItemDialogFragment.show(childFragmentManager, newItemDialogFragment.tag)
        }
    }

    private fun initToolbar(title: String) {
        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = title
        }
        binding.toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun setEmptyTvVisibility(shoppingItems: List<ShoppingItem>) {
        binding.tvEmpty.visibility =
            if (shoppingItems.isNullOrEmpty())
                View.VISIBLE
            else
                View.GONE
    }

    private fun hideProgressBar() {
        binding.layoutProgress.root.visibility = View.GONE
    }
}