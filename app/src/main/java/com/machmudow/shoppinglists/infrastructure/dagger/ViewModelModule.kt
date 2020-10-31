package com.machmudow.shoppinglists.infrastructure.dagger

import androidx.lifecycle.ViewModel
import com.machmudow.shoppinglists.feature.list.current.new.NewListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewListViewModel::class)
    abstract fun bindNewListViewModel(viewModel: NewListViewModel): ViewModel
}