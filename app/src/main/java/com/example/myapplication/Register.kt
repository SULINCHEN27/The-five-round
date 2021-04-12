package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.databinding.RegisterBinding
import kotlin.concurrent.thread

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = RegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userDao = AppDatabase.getDatabase(this).userDao()
        binding.register.setOnClickListener {
            var re_account = binding.usernameRegister.string()
            var re_password = binding.passwordRegister.string()
            var user = User(re_account, re_password)


            thread {
                val find_account = userDao.findUsername(re_account)
                if (find_account != 0) {
                    Looper.prepare()
                    AlertDialog.Builder(this).apply {
                        setTitle("通知栏")
                        setMessage("用户名已存在")
                        setPositiveButton("确定") { dialog, which ->
                        }
                        show()
                    }
                    Looper.loop()
                } else {
                    val intent = Intent(this, Login::class.java)
                    userDao.insertUser(user)
                    Looper.prepare()
                  Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                        Looper.loop()
                    }
                }


            }
        }
    }
