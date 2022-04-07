package com.example.coctailbuddy.room

import android.content.Context
import androidx.room.Room

object DataBaseInstance {
    private var INSTANCE: CoctailDatabase? = null

    fun getDatabase(context: Context): CoctailDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                CoctailDatabase::class.java,
                "COCTAILDATABASE"
            ).build()

            INSTANCE = instance
            // return instance
            instance
        }
    }
}