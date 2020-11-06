package com.machmudow.shoppinglists.feature.list.details

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.FragmentNewItemBinding
import com.machmudow.shoppinglists.base.BaseDaggerDialogFragment
import com.machmudow.shoppinglists.utils.ButtonUtils.disableBtn
import com.machmudow.shoppinglists.utils.ButtonUtils.enableBtn
import com.machmudow.shoppinglists.utils.Status
import com.machmudow.shoppinglists.utils.ToastUtils.showShortToast
import com.machmudow.shoppinglists.utils.ValidationUtils

open class BaseItemDialogFragment : BaseDaggerDialogFragment(R.layout.fragment_new_item) {

    val binding get() = _binding as FragmentNewItemBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewItemBinding.bind(view)

        onCancelClick()
    }

    fun onConfirmClick(confirmFun: () -> Unit) {
        binding.btnConfirm.setOnClickListener {
            when {
                !ValidationUtils.validateTitle(
                    binding.etTitle.text.toString()
                ) -> context?.showShortToast(R.string.title_required)
                !ValidationUtils.validateQuantity(
                    binding.etQuantity.text.toString()
                ) -> context?.showShortToast(R.string.quantity_required)
                else -> confirmFun()
            }
        }
    }

    fun observeStatus(status: MutableLiveData<Status>) {
        status.observe(viewLifecycleOwner) {
            when (it) {
                Status.SUCCESS -> {
                    clearEditTexts()
                    dismiss()
                }
                Status.LOADING -> {
                    binding.btnConfirm.disableBtn()
                }
                Status.ERROR -> {
                    binding.btnConfirm.enableBtn()
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

    private fun clearEditTexts() {
        binding.etTitle.setText("")
        binding.etQuantity.setText("")
    }
}