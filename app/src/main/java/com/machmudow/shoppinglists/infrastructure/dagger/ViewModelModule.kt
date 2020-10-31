package com.machmudow.shoppinglists.infrastructure.dagger

import androidx.lifecycle.ViewModel
import com.machmudow.shoppinglists.feature.list.current.CurrentListViewModel
import com.machmudow.shoppinglists.feature.list.current.create.NewListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CurrentListViewModel::class)
    abstract fun bindCurrentListViewModel(viewModel: CurrentListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NewListViewModel::class)
    abstract fun bindNewListViewModel(viewModel: NewListViewModel): ViewModel
}