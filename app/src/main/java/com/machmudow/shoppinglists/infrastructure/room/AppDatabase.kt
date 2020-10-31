package com.machmudow.shoppinglists.infrastructure.room

import android.content.Context
import androidx.room.*
import com.machmudow.shoppinglists.infrastructure.model.ListItem

@Database(
    entities = [
        ListItem::class
    ],
    version = 1
)

//@TypeConverters(Converters::class)
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

//abstract fun getDAO
}