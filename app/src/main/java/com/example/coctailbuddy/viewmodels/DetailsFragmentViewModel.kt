package com.example.coctailbuddy.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coctailbuddy.data.CoctailItem
import com.example.coctailbuddy.data.CoctailItemList
import com.example.coctailbuddy.retrofit.CoctailRepository
import com.example.coctailbuddy.room.LocalStorageRepository
import kotlinx.coroutines.*

class DetailsFragmentViewModel: ViewModel() {
    var data = MutableLiveData<CoctailItem>()
    lateinit var LocalDataRepository: LocalStorageRepository

    private val repository = CoctailRepository()

    var fetchCoctailDetailsCallback: (CoctailItemList) -> Unit = {
        data.value = it.drinks[0]
    }

    fun getCoctailDetailsbyID(id:String){
        repository.getCoctailDetails(id,fetchCoctailDetailsCallback)
    }

    fun getCoctailDetail(): MutableLiveData<CoctailItem> {
        return data
    }

    fun setLocalDataRepo(repository: LocalStorageRepository){
            LocalDataRepository = repository
    }

     fun saveToLocalDatabase(fetchedItem: CoctailItem?) {
         CoroutineScope(Dispatchers.IO).launch {
             LocalDataRepository.saveItem(fetchedItem!!)
         }
    }
}