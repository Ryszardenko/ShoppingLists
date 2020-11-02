package com.machmudow.shoppinglists.infrastructure.dagger

import com.machmudow.shoppinglists.feature.MainActivity
import com.machmudow.shoppinglists.feature.list.current.CurrentListFragment
import com.machmudow.shoppinglists.feature.list.current.create.NewListDialogFragment
import com.machmudow.shoppinglists.feature.list.current.edit.EditListDialogFragment
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
    abstract fun bindNewListDialogFragment(): NewListDialogFragment

    @ContributesAndroidInjector
    abstract fun bindEditListDialogFragment(): EditListDialogFragment
}