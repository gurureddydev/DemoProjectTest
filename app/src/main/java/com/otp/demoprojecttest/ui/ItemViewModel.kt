package com.otp.demoprojecttest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.otp.demoprojecttest.model.Item
import com.otp.demoprojecttest.model.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(private val repository: ItemRepository) : ViewModel() {
    private val _items = MutableLiveData<List<Item>?>()
    val items: MutableLiveData<List<Item>?> get() = _items

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getItems()
                if (response.isSuccessful) {
                    val apiResponse = response.body()
                    val itemsList = apiResponse?.images
                    _items.postValue(itemsList)
                } else {
                    _error.postValue("Error Loading data")
                }
            } catch (e: Exception) {
                _error.postValue("Exception: ${e.message}")
            }
        }
    }
}


