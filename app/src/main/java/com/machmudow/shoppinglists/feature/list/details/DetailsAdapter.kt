package com.machmudow.shoppinglists.feature.list.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.RecyclerItemBinding
import com.machmudow.shoppinglists.infrastructure.model.ShoppingItem
import com.machmudow.shoppinglists.base.BaseRecyclerViewAdapter
import com.machmudow.shoppinglists.utils.TextViewUtils.strike

class DetailsAdapter(
    private val listener: DetailsListener,
    private val isArchived: Boolean = false
) :
    BaseRecyclerViewAdapter<ShoppingItem>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = RecyclerItemBinding.bind(holder.itemView)
        val item = list[position]
        with(binding) {
            tvTitle.text = item.title
            tvQuantity.text = binding.root.context.getString(R.string.x_quantity, item.quantity)
            tvQuantity.strike = item.isInCart
            setInCartBtnDrawable(btnInCart, item)
            prepareView(binding, item)
        }
    }

    private fun prepareView(binding: RecyclerItemBinding, item: ShoppingItem) {
        if (isArchived)
            setArchivedView(binding)
        else {
            setNotArchivedView(binding, item)
        }
    }

    private fun setArchivedView(binding: RecyclerItemBinding) {
        binding.btnInCart.isClickable = false
        binding.btnEdit.visibility = View.GONE
    }

    private fun setNotArchivedView(
        binding: RecyclerItemBinding,
        item: ShoppingItem
    ) {
        onInCartBtnClick(binding.btnInCart, item)
        onEditBtnClick(binding.btnEdit, item)
    }

    private fun onInCartBtnClick(btnInCart: AppCompatImageButton, item: ShoppingItem) {
        btnInCart.setOnClickListener {
            if (item.isInCart)
                listener.removeFromCart(item.id)
            else
                listener.addToCart(item.id)
        }
    }

    private fun setInCartBtnDrawable(btnInCart: AppCompatImageButton, item: ShoppingItem) {
        btnInCart.setImageDrawable(
            ContextCompat.getDrawable(
                btnInCart.context,
                if (!item.isInCart)
                    R.drawable.ic_unchecked
                else
                    R.drawable.ic_checked
            )
        )
    }

    private fun onEditBtnClick(
        btnEdit: AppCompatImageButton,
        shoppingItem: ShoppingItem
    ) {
        btnEdit.setOnClickListener {
            listener.showDialog(shoppingItem)
        }
    }
}

interface DetailsListener {
    fun addToCart(shoppingItemId: Int)
    fun removeFromCart(shoppingItemId: Int)
    fun showDialog(shoppingItem: ShoppingItem)
}