package com.machmudow.shoppinglists.infrastructure.dagger

import com.machmudow.shoppinglists.feature.MainActivity
import com.machmudow.shoppinglists.feature.list.archived.ArchivedListFragment
import com.machmudow.shoppinglists.feature.list.current.CurrentListFragment
import com.machmudow.shoppinglists.feature.list.current.create.NewListDialogFragment
import com.machmudow.shoppinglists.feature.list.current.edit.EditListDialogFragment
import com.machmudow.shoppinglists.feature.list.details.DetailsFragment
import com.machmudow.shoppinglists.feature.list.details.create.NewItemDialogFragment
import com.machmudow.shoppinglists.feature.list.details.edit.EditItemDialogFragment
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
abstract class ViewsBindingModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindCurrentListFragment(): CurrentListFragment

    @ContributesAndroidInjector
    abstract fun bindArchivedListFragment(): ArchivedListFragment

    @ContributesAndroidInjector
    abstract fun bindDetailsFragment(): DetailsFragment

    @ContributesAndroidInjector
    abstract fun bindNewListDialogFragment(): NewListDialogFragment

    @ContributesAndroidInjector
    abstract fun bindEditListDialogFragment(): EditListDialogFragment

    @ContributesAndroidInjector
    abstract fun bindNewItemDialogFragment(): NewItemDialogFragment

    @ContributesAndroidInjector
    abstract fun bindEditItemDialogFragment(): EditItemDialogFragment
}