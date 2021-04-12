package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insertUser(user:User):Long

    @Query("select * from User where username=:name")
    fun findUsername(name:String):Int

    @Query("select * from User where username=:name")
    fun findPassword(name: String):User

}