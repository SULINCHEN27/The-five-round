package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityMainBinding
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private val programList = ArrayList<Program>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUrl = arrayListOf<Int>(
            R.drawable.one, R.drawable.two,
            R.drawable.three, R.drawable.four
        )

        val imageTitle = arrayListOf<String>("one", "two", "three", "four")
        //设置内置样式
        binding.banner.setBannerStyle(1)
        //设置图片加载器
        binding.banner.setImageLoader(MyLoader())
        //设置图片集合
        binding.banner.setImages(imageUrl)
        //设置轮播图的标题集合
        binding.banner.setBannerTitles(imageTitle)
        //轮播间隔时间
        binding.banner.setDelayTime(2000)
        //设置是否为自动轮播
        binding.banner.isAutoPlay(true)
        //设置指示器的位置
        binding.banner.setIndicatorGravity(BannerConfig.CENTER)
        var i = 0;
        //设置点击事件
        binding.banner.setOnBannerListener {
            i++;
            binding.banner.isAutoPlay(false)
            Log.d("轮播图", it.dec().toString())
            if (i != 1) {
                when (it.dec()) {
                    -1 -> {
                        val intent = Intent(this, WechatSystem::class.java)
                        startActivity(intent)
                    }
                    0 -> {
                        val intent = Intent(this, WebDesign::class.java)
                        startActivity(intent)
                    }
                    1 -> {
                        val intent = Intent(this, AppDevelopment::class.java)
                        startActivity(intent)
                    }
                    2 -> {
                        val intent = Intent(this, SystemDevelopment::class.java)
                        startActivity(intent)
                    }
                }
            }

        }
        //必须最后调用方法，启动轮播图
        binding.banner.start()

        initFruit()
        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = ProgramAdapter(programList)
        binding.recyclerView.adapter = adapter

    }

    private fun initFruit() {

            programList.add(Program("SIREN", R.drawable.a))
            programList.add(Program("SEAL TEAM", R.drawable.b))
            programList.add(Program("PUNISHER", R.drawable.c))
            programList.add(Program("THIS IS YOUR DEATH", R.drawable.d))
            programList.add(Program("DEFIANCE", R.drawable.e))
            programList.add(Program("Earth Day", R.drawable.f))
            programList.add(Program("Help build more facilities adapted for disabled people", R.drawable.g))
            programList.add(Program("Water changes everything", R.drawable.i))
            programList.add(Program("BYCLE PAMIATKA BOLI", R.drawable.j))
            programList.add(Program("MORE SPACE FOR THE BIG ONES", R.drawable.k))



    }


}



private class MyLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        Glide.with(context).load(path as Int).into(imageView)
    }




}