package com.machmudow.shoppinglists.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.machmudow.shoppinglists.R
import com.machmudow.shoppinglists.infrastructure.dagger.ViewModelFactory
import dagger.android.support.DaggerDialogFragment
import javax.inject.Inject

abstract class BaseDaggerDialogFragment(private val layoutId: Int) : DaggerDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var _binding: ViewBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getTheme(): Int {
        return R.style.Dialog_Transparent
    }
}