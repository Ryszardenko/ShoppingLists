package com.machmudow.shoppinglists.feature.list.current

import android.view.LayoutInflater
import android.view.ViewGroup
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.RecyclerShoppingListBinding
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import com.machmudow.shoppinglists.utils.BaseRecyclerViewAdapter

class ListAdapter(
    private val listener: ShoppingListListener
) : BaseRecyclerViewAdapter<ShoppingList>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_shopping_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = RecyclerShoppingListBinding.bind(holder.itemView)
        val shoppingList = list[position]
        with(binding) {
            tvTitle.text = shoppingList.title
            tvDate.text = shoppingList.date
            btnEdit.setOnClickListener {
                listener.showDialog(shoppingList)
            }
            layoutContainer.setOnClickListener {
                listener.navigateTo(shoppingList.id)
            }
        }
    }
}

interface ShoppingListListener {
    fun showDialog(shoppingList: ShoppingList)
    fun navigateTo(shoppingListId: Int)
}