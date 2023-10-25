package com.example.todolist

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {
    @Insert
    fun insert(user: User)
    @Delete
    fun deleteUser(user: User)

    @Query("UPDATE User_Details SET UserName =:username where Serial_No =:position")
    fun updateData(position: String, username: String)

    @Query("SELECT * FROM User_Details")
    fun getAllData(): List<User>

}