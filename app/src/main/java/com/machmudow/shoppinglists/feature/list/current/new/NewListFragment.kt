package com.machmudow.shoppinglists.feature.list.current.new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.databinding.FragmentNewListBinding
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList
import com.machmudow.shoppinglists.utils.BaseDialogFragment
import javax.inject.Inject

class NewListFragment : BaseDialogFragment(R.layout.fragment_new_list) {

    companion object {
        fun newInstance() = NewListFragment()
    }

    @Inject
    lateinit var viewModel: NewListViewModel

    private val binding get() = _binding as FragmentNewListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_list, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewListBinding.bind(view)

        viewModel = ViewModelProvider(this, viewModelFactory).get(NewListViewModel::class.java)

        onCancelClick()
        onCreateClick()
    }

    override fun getTheme(): Int {
        return R.style.Dialog_Transparent
    }

    private fun onCancelClick() {
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun onCreateClick() {
        binding.btnCreate.setOnClickListener {
            val shoppingList = createShoppingList()

            viewModel.createShoppingList(shoppingList)
        }
    }

    private fun createShoppingList(): ShoppingList {
        val title = binding.etTitle.text.toString()
        val description = binding.etDescription.text.toString()

        return ShoppingList(title = title, description = description)
    }
}