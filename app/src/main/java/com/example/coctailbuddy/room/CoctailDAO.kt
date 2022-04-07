package com.example.coctailbuddy.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coctailbuddy.data.CoctailItem

@Dao
interface CoctailDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoctail(coctail: CoctailItem)

    @Query("SELECT COUNT(*) FROM COCTAILDATABASE")
    suspend fun countItems(): Int

    @Query("DELETE FROM COCTAILDATABASE WHERE idDrink = :idDrink")
    suspend fun deleteItembyName(idDrink: String)

    @Query("SELECT * FROM COCTAILDATABASE")
    suspend fun getAllItems(): List<CoctailItem>
}