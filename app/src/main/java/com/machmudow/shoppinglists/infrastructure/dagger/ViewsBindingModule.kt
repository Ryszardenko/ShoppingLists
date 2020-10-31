package com.machmudow.shoppinglists.infrastructure.dagger

import com.machmudow.shoppinglists.feature.MainActivity
import com.machmudow.shoppinglists.feature.list.current.CurrentListFragment
import com.machmudow.shoppinglists.feature.list.current.create.NewListDaggerDialogFragment
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
    abstract fun bindNewListDialogFragment(): NewListDaggerDialogFragment
}