package com.machmudow.shoppinglists.feature.list.details.create

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.FragmentNewItemBinding
import com.machmudow.shoppinglists.infrastructure.model.ShoppingItem
import com.machmudow.shoppinglists.utils.*
import com.machmudow.shoppinglists.utils.Constants.SHOPPING_LIST_ID
import com.machmudow.shoppinglists.utils.ToastUtils.showShortToast
import javax.inject.Inject

class NewItemDialogFragment : BaseDaggerDialogFragment(R.layout.fragment_new_item) {

    companion object {
        fun newInstance(shoppingListId: Int): NewItemDialogFragment {
            val args = Bundle()
            args.putInt(SHOPPING_LIST_ID, shoppingListId)
            val fragment = NewItemDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var viewModel: NewItemDialogViewModel

    val binding get() = _binding as FragmentNewItemBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewItemBinding.bind(view)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(NewItemDialogViewModel::class.java)

        val shoppingListId = arguments?.getInt(SHOPPING_LIST_ID)

        onConfirmClick(shoppingListId)
        observeStatus(viewModel.status)
        onCancelClick()
    }

    override fun getTheme(): Int {
        return R.style.Dialog_Transparent
    }

    private fun onConfirmClick(shoppingListId: Int?) {
        binding.btnConfirm.setOnClickListener {
            shoppingListId?.let {
                val shoppingItem = createShoppingItem(shoppingListId)

                when {
                    binding.etTitle.text.isNullOrBlank() -> context?.showShortToast(R.string.title_required)
                    binding.etQuantity.text.isNullOrBlank() -> context?.showShortToast(R.string.quantity_required)
                    else -> viewModel.insertShoppingItem(shoppingItem)
                }
            }
        }
    }

    private fun observeStatus(status: MutableLiveData<Status>) {
        status.observe(viewLifecycleOwner) {
            when (it) {
                Status.SUCCESS -> {
                    clearEditTexts()
                    dismiss()
                }
                Status.LOADING -> {
                    ButtonUtils.disableBtn(binding.btnConfirm)
                }
                Status.ERROR -> {
                    ButtonUtils.enableBtn(binding.btnConfirm)
                    context?.showShortToast(R.string.error)
                }
                else -> {
                }
            }
        }
    }

    private fun onCancelClick() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun createShoppingItem(shoppingListId: Int): ShoppingItem {
        return ShoppingItem(
            shoppingListId = shoppingListId,
            title = binding.etTitle.text.toString(),
            quantity = binding.etQuantity.text.toString().toInt(),
        )
    }

    private fun clearEditTexts() {
        binding.etTitle.setText("")
        binding.etQuantity.setText("")
    }
}