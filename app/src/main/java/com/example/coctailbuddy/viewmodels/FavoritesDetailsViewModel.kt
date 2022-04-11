package com.example.coctailbuddy.viewmodels

import androidx.lifecycle.ViewModel
import com.example.coctailbuddy.room.LocalStorageRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesDetailsViewModel : ViewModel() {
    private lateinit var repo: LocalStorageRepository

    fun deleteItem(id: String) {
        CoroutineScope(Dispatchers.IO).launch { repo.deleteCoctail(id) }
    }

    fun setRepo(repository: LocalStorageRepository) {
        this.repo = repository
    }
}