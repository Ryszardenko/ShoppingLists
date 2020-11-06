package com.machmudow.shoppinglists.feature.list.current

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.FragmentNewListBinding
import com.machmudow.shoppinglists.base.BaseDaggerDialogFragment
import com.machmudow.shoppinglists.utils.ButtonUtils.disableBtn
import com.machmudow.shoppinglists.utils.ButtonUtils.enableBtn
import com.machmudow.shoppinglists.utils.Status
import com.machmudow.shoppinglists.utils.ToastUtils.showShortToast
import com.machmudow.shoppinglists.utils.ValidationUtils

open class BaseListDialogFragment : BaseDaggerDialogFragment(R.layout.fragment_new_list) {

    val binding get() = _binding as FragmentNewListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewListBinding.bind(view)

        onCancelClick()
    }

    fun onConfirmClick(confirmFun: () -> Unit) {
        binding.btnConfirm.setOnClickListener {
            if (!ValidationUtils.validateTitle(binding.etTitle.text.toString()))
                context?.showShortToast(R.string.title_required)
            else
                confirmFun()
        }
    }

    fun observeStatus(status: MutableLiveData<Status>) {
        status.observe(viewLifecycleOwner) {
            when (it) {
                Status.SUCCESS -> {
                    clearTitle()
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

    private fun clearTitle() {
        binding.etTitle.setText("")
    }

    private fun onCancelClick() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}