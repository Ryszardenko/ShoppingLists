package com.machmudow.shoppinglists.infrastructure.room

import android.content.Context
import androidx.room.*
import com.machmudow.shoppinglists.infrastructure.model.ShoppingItem
import com.machmudow.shoppinglists.infrastructure.model.ShoppingList

@Database(
    entities = [
        ShoppingList::class,
        ShoppingItem::class
    ],
    version = 1
)

@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: buildDatabase(
                        context
                    ).also {
                        instance = it
                    }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "appdatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    abstract fun getShoppingListDAO(): ShoppingListDAO
    abstract fun getShoppingItemDAO(): ShoppingItemDAO
}