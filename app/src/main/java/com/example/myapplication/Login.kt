package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.LoginBinding
import kotlin.concurrent.thread

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userDao = AppDatabase.getDatabase(this).userDao()
        binding.login.setOnClickListener {
            val str = binding.accountEdit.string()
            val pw = binding.passwordEdit.string()
            thread {

                val isUsername = userDao.findUsername(str)
                if(isUsername==0){
                    Looper.prepare()
                    AlertDialog.Builder(this).apply {
                        setTitle("通知栏")
                        setMessage("该用户名没有注册")
                        setPositiveButton("确定") { dialog, which ->
                        }
                        show()
                    }
                    Looper.loop()
                }else{
                    val user = userDao.findPassword(str)
                    if(user.password==pw){
                        val intent = Intent(this,MainActivity::class.java)
                        Looper.prepare()
                        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                        Looper.loop()
                    }else{
                        Looper.prepare()
                        AlertDialog.Builder(this).apply {
                            setTitle("通知栏")
                            setMessage("用户名或密码错误")
                            setPositiveButton("确定") { dialog, which ->
                            }
                            show()
                        }
                        Looper.loop()
                    }
                }


            }

        }

        binding.register.skipActivity(Register())





    }
}