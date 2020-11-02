package com.machmudow.shoppinglists.feature.list.current

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.FragmentNewListBinding
import com.machmudow.shoppinglists.utils.BaseDaggerDialogFragment
import com.machmudow.shoppinglists.utils.ButtonUtils
import com.machmudow.shoppinglists.utils.Status
import com.machmudow.shoppinglists.utils.ToastUtils.showShortToast

open class BaseListDialogFragment : BaseDaggerDialogFragment(R.layout.fragment_new_list) {

    val binding get() = _binding as FragmentNewListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewListBinding.bind(view)

        onCancelClick()
    }

    override fun getTheme(): Int {
        return R.style.Dialog_Transparent
    }

    fun onConfirmClick(confirmFun: () -> Unit) {
        binding.btnConfirm.setOnClickListener {
            if (binding.etTitle.text.isNullOrBlank())
                context?.showShortToast(R.string.title_required)
            else
                confirmFun()
        }
    }

    fun observeStatus(status: MutableLiveData<Status>) {
        status.observe(viewLifecycleOwner) {
            when (it) {
                Status.SUCCESS -> {
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

    fun clearEditTexts() {
        binding.etTitle.setText("")
        binding.etDescription.setText("")
    }

    private fun onCancelClick() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }
}