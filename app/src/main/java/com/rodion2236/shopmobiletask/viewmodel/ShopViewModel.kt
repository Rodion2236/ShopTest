package com.rodion2236.shopmobiletask.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodion2236.shopmobiletask.model.flash.InfoFlash
import com.rodion2236.shopmobiletask.model.latest.InfoLatest
import com.rodion2236.shopmobiletask.repository.ShopRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShopViewModel: ViewModel() {

    private val repository = ShopRepository()

    private var _latest = MutableLiveData<List<InfoLatest>>()
    private var _flash = MutableLiveData<List<InfoFlash>>()

    private val latest: LiveData<List<InfoLatest>> get() = _latest
    private val flash: LiveData<List<InfoFlash>> get() = _flash


    init {
        viewModelScope.launch {
            flashLiveData()
            latestLiveData()
        }
    }

    private suspend fun latestLiveData() {
        withContext(Dispatchers.IO) {
            try {
                val response = repository.getLatest()
                _latest.postValue(response.latest)
            } catch (e: Exception) {
                Log.e("TAG_s", e.message.toString())
            }
        }
    }

    fun observeLatest(): LiveData<List<InfoLatest>> {
        return latest
    }

    private suspend fun flashLiveData() {
        withContext(Dispatchers.IO) {
            try {
                val response = repository.getFlash()
                _flash.postValue(response.flash)
            } catch (e: Exception) {
                Log.e("TAG_s", e.message.toString())
            }
        }
    }

    fun observeFlash(): LiveData<List<InfoFlash>> {
        return flash
    }
}