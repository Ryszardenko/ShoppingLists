package com.machmudow.shoppinglists.feature.list.current

import android.view.LayoutInflater
import android.view.ViewGroup
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.RecyclerListItemBinding
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import com.machmudow.shoppinglists.utils.BaseRecyclerViewAdapter

class ListAdapter(
    private val listener: ShoppingListListener
) : BaseRecyclerViewAdapter<ShoppingList>() {


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
            btnEdit.setOnClickListener {
                listener.showDialog(shoppingList)
            }
        }
    }
}

interface ShoppingListListener {
    fun showDialog(shoppingList: ShoppingList)
}