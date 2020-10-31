package com.machmudow.shoppinglists.infrastructure.dagger

import com.machmudow.shoppinglists.feature.MainActivity
import com.machmudow.shoppinglists.feature.list.current.CurrentListFragment
import com.machmudow.shoppinglists.feature.list.current.new.NewListFragment
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module(includes = [AndroidInjectionModule::class])
abstract class ViewsBindingModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindCurrentListFragment(): CurrentListFragment

    @ContributesAndroidInjector
    abstract fun bindNewListFragment(): NewListFragment
}