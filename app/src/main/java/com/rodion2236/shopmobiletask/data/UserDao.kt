package com.rodion2236.shopmobiletask.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodion2236.shopmobiletask.model.User

@Dao
interface UserDao {

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUserToDatabase(user: User)

    @Query ("SELECT * FROM user ORDER BY id ASC")
    fun readData(): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE email = :email AND firstName = :firstName")
    fun getEmailAndFirstName(email: String, firstName: String): LiveData<User>

    @Query("SELECT * FROM user WHERE firstName = :firstName")
    fun authentication(firstName: String): LiveData<User>
}