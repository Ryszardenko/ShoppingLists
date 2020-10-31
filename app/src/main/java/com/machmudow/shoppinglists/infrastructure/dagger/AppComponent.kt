package com.machmudow.shoppinglists.infrastructure.dagger

import android.app.Application
import com.machmudow.shoppinglists.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
//        NetModule::class,
        DatabaseModule::class,
        ViewsBindingModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
//        fun netModule(netModule: NetModule): Builder
        fun databaseModule(databaseModule: DatabaseModule): Builder
        fun build(): AppComponent
    }

}