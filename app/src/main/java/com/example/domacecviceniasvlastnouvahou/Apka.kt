package com.example.domacecviceniasvlastnouvahou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment

class Apka : AppCompatActivity() {

    private lateinit var workoutImgViewA: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workoutImgViewA = findViewById(R.id.workoutImgView)
        workoutImgViewA.setOnClickListener {
            val intent = Intent(this, ZoznamCvikov::class.java)
            startActivity(intent)
        }

    }
}