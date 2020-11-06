package com.machmudow.shoppinglists.feature.list.details.edit

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.machmudow.shoppinglists.databinding.FragmentNewItemBinding
import com.machmudow.shoppinglists.feature.list.details.BaseItemDialogFragment
import com.machmudow.shoppinglists.infrastructure.model.ShoppingItem
import com.machmudow.shoppinglists.utils.Constants.SHOPPING_ITEM
import javax.inject.Inject

class EditItemDialogFragment : BaseItemDialogFragment() {

    companion object {
        fun newInstance(shoppingItem: ShoppingItem): EditItemDialogFragment {
            val args = Bundle()
            args.putSerializable(SHOPPING_ITEM, shoppingItem)
            val fragment = EditItemDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var viewModel: EditItemDialogViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewItemBinding.bind(view)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(EditItemDialogViewModel::class.java)

        val shoppingItem = arguments?.getSerializable(SHOPPING_ITEM) as ShoppingItem?

        shoppingItem?.let {
            setEditTexts(shoppingItem)
            onConfirmClick { viewModel.updateShoppingItem(editShoppingItem(shoppingItem)) }
        }

        observeStatus(viewModel.status)
    }

    private fun setEditTexts(shoppingItem: ShoppingItem) {
        binding.etTitle.setText(shoppingItem.title)
        binding.etQuantity.setText(shoppingItem.quantity.toString())
    }

    private fun editShoppingItem(shoppingItem: ShoppingItem): ShoppingItem {
        return shoppingItem.copy(
            title = binding.etTitle.text.toString(),
            quantity = binding.etQuantity.text.toString().toInt()
        )
    }
}