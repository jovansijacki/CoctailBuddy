package com.example.coctailbuddy.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coctailbuddy.data.CoctailItem

@Database(entities = [CoctailItem::class], version = 1)
abstract class CoctailDatabase : RoomDatabase() {

    abstract fun getCoctailDao(): CoctailDAO

}