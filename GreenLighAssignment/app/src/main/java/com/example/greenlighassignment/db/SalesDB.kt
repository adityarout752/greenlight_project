package com.example.greenlighassignment.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.greenlighassignment.utils.SalesTypeConverter


@Database(entities = [EntityClass::class], version = 1, exportSchema = false)
@TypeConverters(SalesTypeConverter::class)
abstract class SalesDB : RoomDatabase() {

    abstract fun salesDao(): SalesDao

    companion object {
        // Singleton prevents multiple
        // instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SalesDB? = null

        fun getDatabase(context: Context): SalesDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SalesDB::class.java,
                    "sales_db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
