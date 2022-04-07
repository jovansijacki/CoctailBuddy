package com.example.coctailbuddy.room

import com.example.coctailbuddy.data.CoctailItem

class LocalStorageRepository(private val database: CoctailDatabase) {

    suspend fun getItemCount(): Int {
        return database.getCoctailDao().countItems()
    }

    suspend fun saveItem(item: CoctailItem) {
        return database.getCoctailDao().insertCoctail(item)
    }

    suspend fun deleteCoctail(name: String) {
        return database.getCoctailDao().deleteItembyName(name)
    }

    suspend fun getAllCoctails(): List<CoctailItem> {
        return database.getCoctailDao().getAllItems()
    }
}