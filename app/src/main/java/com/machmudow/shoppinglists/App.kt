package com.machmudow.shoppinglists

import com.facebook.stetho.Stetho
import com.machmudow.shoppinglists.infrastructure.dagger.AppComponent
import com.machmudow.shoppinglists.infrastructure.dagger.DaggerAppComponent
import com.machmudow.shoppinglists.infrastructure.dagger.DatabaseModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        initializeStetho()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = buildDaggerComponent()

        appComponent.inject(this)
        return appComponent
    }

    private fun initializeStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun buildDaggerComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .application(this)
            .databaseModule(DatabaseModule(applicationContext))
            .build()
    }
}