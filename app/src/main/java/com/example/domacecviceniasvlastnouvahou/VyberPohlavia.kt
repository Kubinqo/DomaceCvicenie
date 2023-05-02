package com.example.domacecviceniasvlastnouvahou

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView


class VyberPohlavia : AppCompatActivity() {

    private var isImageMuzSelected = false
    private var isImageZenaSelected = false
    private lateinit var muz: ImageView
    private lateinit var zena: ImageView
    private lateinit var buttonDalej: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vyber_pohlavia)

        //inicializacia
        muz = findViewById(R.id.pohlavieMuzView)
        zena = findViewById(R.id.pohlavieZenaView)
        buttonDalej = findViewById(R.id.dalejBtn)

        //kontrola ci je muz oznaceny
        muz.setOnClickListener {
            isImageMuzSelected = !isImageMuzSelected
            if (isImageMuzSelected) {
                muz.setBackgroundResource(R.drawable.oznacenie_obrysu)
                isImageZenaSelected = false
                zena.setBackgroundResource(R.drawable.odznacenie_obrysu)
                buttonDalej.setTextColor(Color.WHITE)
                buttonDalej.isEnabled = true
            } else {
                muz.setBackgroundResource(R.drawable.odznacenie_obrysu)
                buttonDalej.setTextColor(Color.GRAY)
                buttonDalej.isEnabled = false
            }
        }

        zena.setOnClickListener {
            isImageZenaSelected = !isImageZenaSelected
            if (isImageZenaSelected) {
                zena.setBackgroundResource(R.drawable.oznacenie_obrysu)
                isImageMuzSelected = false
                muz.setBackgroundResource(R.drawable.odznacenie_obrysu)
                buttonDalej.setTextColor(Color.WHITE)
                buttonDalej.isEnabled = true
            } else {
                zena.setBackgroundResource(R.drawable.odznacenie_obrysu)
                buttonDalej.setTextColor(Color.GRAY)
                buttonDalej.isEnabled = false
            }
        }

        buttonDalej.setOnClickListener() {
            val intent = Intent(this, VyberPocetKlikov::class.java)
            startActivity(intent)
        }

    }
}