package com.example.att

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var topAnim : Animation
    lateinit var bottomAnim : Animation
    lateinit var image : ImageView
    lateinit var logo: TextView
    lateinit var slogan: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Removes status bar
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN,)
        setContentView(R.layout.activity_main)

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation)

        image=findViewById(R.id.image_logo)
        logo=findViewById(R.id.text_logo)
        slogan=findViewById(R.id.text_slogan)

        image.animation = topAnim
        logo.animation = bottomAnim
        slogan.animation = bottomAnim

        val runnable = Runnable{
                startActivity(Intent(this@MainActivity, Login::class.java))
                finish()
            }
        Handler().postDelayed(runnable, 2500)
    }

        }