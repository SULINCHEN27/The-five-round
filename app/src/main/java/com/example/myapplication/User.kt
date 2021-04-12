package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(var username:String,var password:String) {

    @PrimaryKey(autoGenerate = true)
    var id:Long=0
}