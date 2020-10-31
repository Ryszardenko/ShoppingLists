package com.machmudow.shoppinglists.infrastructure.dagger

import android.content.Context
import com.machmudow.shoppinglists.infrastructure.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideDatabase() = AppDatabase(context)

    @Provides
    @Singleton
    fun provideShoppingListDAO(appDatabase: AppDatabase) = appDatabase.getShoppingListDAO()

    @Provides
    @Singleton
    fun provideShoppingItemDAO(appDatabase: AppDatabase) = appDatabase.getShoppingItemDAO()
}