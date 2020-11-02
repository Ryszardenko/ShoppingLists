package com.machmudow.shoppinglists.feature.list.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.RecyclerItemBinding
import com.machmudow.shoppinglists.infrastructure.model.ShoppingItem
import com.machmudow.shoppinglists.utils.BaseRecyclerViewAdapter

class DetailsAdapter(private val listener: DetailsListener) :
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
            tvQuantity.text = item.quantity.toString()
            setInCartBtnDrawable(btnInCart, item)
            btnInCart.setOnClickListener {
                if (item.isInCart)
                    listener.removeFromCart()
                else
                    listener.addToCart()
            }
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
}

interface DetailsListener {
    fun addToCart()
    fun removeFromCart()
}