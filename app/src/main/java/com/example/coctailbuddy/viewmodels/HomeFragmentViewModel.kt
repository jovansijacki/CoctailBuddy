package com.example.coctailbuddy.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.coctailbuddy.data.SmallCoctailItemList
import com.example.coctailbuddy.retrofit.CoctailRepository

class HomeFragmentViewModel : ViewModel() {

    var dataList = MutableLiveData<SmallCoctailItemList>()

    private val repository = CoctailRepository()

    var fetchCoctailsCallback: (SmallCoctailItemList) -> Unit = {
        dataList.value = it
    }

    fun getAllCoctails() {
        repository.getAllCoctails(fetchCoctailsCallback)
    }

    fun getCoctails(): LiveData<SmallCoctailItemList> {
        return dataList
    }
}