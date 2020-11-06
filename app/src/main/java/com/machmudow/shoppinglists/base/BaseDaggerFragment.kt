package com.machmudow.shoppinglists.base

import androidx.viewbinding.ViewBinding
import com.machmudow.shoppinglists.infrastructure.dagger.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseDaggerFragment(layoutId: Int) : DaggerFragment(layoutId) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var _binding: ViewBinding? = null

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}