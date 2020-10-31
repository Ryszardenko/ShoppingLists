package com.machmudow.shoppinglists.utils

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder>() {

    open val list: MutableList<T> = mutableListOf()

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder

    override fun getItemCount(): Int {
        return list.size
    }

    abstract override fun onBindViewHolder(holder: ViewHolder, position: Int)

    open fun setRecyclerList(list: List<T>?) {
        list?.let {
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}