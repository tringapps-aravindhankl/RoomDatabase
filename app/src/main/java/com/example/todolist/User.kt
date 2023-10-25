package com.example.todolist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "User_Details")
data class User(



    @ColumnInfo(name = "UserName")
    var UserName: String,

    @ColumnInfo(name = "PassWord")
    var Password: String,

    @PrimaryKey()
    @ColumnInfo(name = "Serial_No")
    var serialNo: Int




)