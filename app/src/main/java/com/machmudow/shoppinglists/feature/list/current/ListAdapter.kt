package com.machmudow.shoppinglists.feature.list.current

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.RecyclerShoppingListBinding
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import com.machmudow.shoppinglists.infrastructure.model.ShoppingListWithItems
import com.machmudow.shoppinglists.base.BaseRecyclerViewAdapter

class ListAdapter(
    private val listener: ShoppingListListener
) : BaseRecyclerViewAdapter<ShoppingListWithItems>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_shopping_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = RecyclerShoppingListBinding.bind(holder.itemView)
        val shoppingListWithItems = list[position]
        with(binding) {
            tvTitle.text = shoppingListWithItems.shoppingList.title

            bindInCartTv(tvInCart, shoppingListWithItems)
            onEditBtnClick(btnEdit, shoppingListWithItems)
            onContainerLayoutClick(layoutContainer, shoppingListWithItems)
        }
    }

    private fun bindInCartTv(
        tvInCart: TextView,
        shoppingListWithItems: ShoppingListWithItems
    ) {
        val shoppingItems = shoppingListWithItems.shoppingItems.size
        val inCartShoppingItems =
            shoppingListWithItems.shoppingItems.filter { it.isInCart }.size
        tvInCart.text = tvInCart.context.getString(
            R.string.groceries_done,
            inCartShoppingItems,
            shoppingItems
        )
    }

    private fun onEditBtnClick(
        btnEdit: AppCompatImageButton,
        shoppingListWithItems: ShoppingListWithItems
    ) {
        btnEdit.setOnClickListener {
            listener.showDialog(shoppingListWithItems.shoppingList)
        }
    }

    private fun onContainerLayoutClick(
        layoutContainer: LinearLayout,
        shoppingListWithItems: ShoppingListWithItems
    ) {
        layoutContainer.setOnClickListener {
            listener.navigateTo(shoppingListWithItems.shoppingList.id)
        }
    }
}

interface ShoppingListListener {
    fun showDialog(shoppingList: ShoppingList)
    fun navigateTo(shoppingListId: Int)
}