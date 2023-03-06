package com.rodion2236.shopmobiletask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.rodion2236.shopmobiletask.data.AppDatabase
import com.rodion2236.shopmobiletask.model.User
import com.rodion2236.shopmobiletask.repository.UserRepository
import kotlinx.coroutines.launch

class ViewModel(application: Application): AndroidViewModel(application) {

    private val readData: LiveData<List<User>>
    private val reipository: UserRepository

    init {
        val userDao = AppDatabase.getDb(application).userDao()

        reipository = UserRepository(userDao)
        readData = reipository.readData
    }

    fun addUserToDatabase(user: User) {
        viewModelScope.launch {
            reipository
                .addUserToDatabase(user)
        }
    }

    fun getEmailAndFirstName(email: String, firstName: String): LiveData<User> {
        return reipository
            .getEmailAndFirstName(email, firstName)
    }

    fun authentication(firstName: String): LiveData<User> {
        return reipository
            .authentication(firstName)
    }
}