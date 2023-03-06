package com.rodion2236.shopmobiletask.repository

import androidx.lifecycle.LiveData
import com.rodion2236.shopmobiletask.data.UserDao
import com.rodion2236.shopmobiletask.model.User

class UserRepository(
    private val userDao: UserDao
    ) {

    val readData: LiveData<List<User>> = userDao.readData()

    suspend fun addUserToDatabase(user: User) {
        userDao.addUserToDatabase(user)
    }

    fun getEmailAndFirstName(email: String, firstName: String): LiveData<User> {
        return userDao.getEmailAndFirstName(email, firstName)
    }

    fun authentication(firstName: String): LiveData<User> {
        return userDao.authentication(firstName)
    }
}