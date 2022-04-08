package com.example.coctailbuddy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.coctailbuddy.data.CoctailItem
import com.example.coctailbuddy.room.LocalStorageRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class FavoritesFragmentViewModel : ViewModel() {

    private lateinit var LocalDataRepository: LocalStorageRepository

    fun getNumberOfCoctails(): Deferred<Int> {
        return CoroutineScope(Dispatchers.IO).async { LocalDataRepository.getItemCount() }
    }

    fun getAllCoctailsFromLocalDatabase(): Deferred<List<CoctailItem>> {
        return CoroutineScope(Dispatchers.IO).async { LocalDataRepository.getAllCoctails() }
    }

    fun setLocalDataRepo(repo: LocalStorageRepository) {
        this.LocalDataRepository = repo
    }
}