package com.machmudow.shoppinglists.feature.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.RecyclerListItemBinding
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import com.machmudow.shoppinglists.utils.BaseRecyclerViewAdapter

class ShoppingListAdapter : BaseRecyclerViewAdapter<ShoppingList>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = RecyclerListItemBinding.bind(holder.itemView)
        val shoppingList = list[position]
        with(binding) {
            tvTitle.text = shoppingList.title
            tvDate.text = shoppingList.date
        }
    }
}