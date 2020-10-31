package com.machmudow.shoppinglists.infrastructure.dagger

import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
abstract class ViewsBindingModule {

//    @ContributesAndroidInjector
//    abstract fun bindSplashActivity(): SplashActivity
}