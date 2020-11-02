package com.machmudow.shoppinglists.infrastructure.dagger

import androidx.lifecycle.ViewModel
import com.machmudow.shoppinglists.feature.list.current.CurrentListViewModel
import com.machmudow.shoppinglists.feature.list.current.create.NewListDialogViewModel
import com.machmudow.shoppinglists.feature.list.current.edit.EditListDialogViewModel
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
    @ViewModelKey(NewListDialogViewModel::class)
    abstract fun bindNewListViewModel(viewModel: NewListDialogViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditListDialogViewModel::class)
    abstract fun bindEditListViewModel(viewModel: EditListDialogViewModel): ViewModel
}