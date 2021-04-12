package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil.setContentView
import com.example.myapplication.databinding.LoginBinding
import java.util.*
import kotlin.concurrent.thread

class CustomEdit(context: Context,attrs:AttributeSet):LinearLayout(context,attrs){
    init {
        LayoutInflater.from(context).inflate(R.layout.edit,this)
    }

    fun string():String{
        val view = LayoutInflater.from(context).inflate(R.layout.edit,this)
        val editText = view.findViewById<EditText>(R.id.edit)
        val str = editText.text.toString()
        return str
    }


}


class CustomButton(context: Context,attrs: AttributeSet):LinearLayout(context,attrs){

    init {
        LayoutInflater.from(context).inflate(R.layout.button,this)

    }

    fun skipActivity(activity: Activity){
        val button = findViewById<Button>(R.id.button)
        val intent = Intent(context,activity::class.java)
        button.setOnClickListener {
            startActivity(context,intent, Bundle())
        }


    }
}