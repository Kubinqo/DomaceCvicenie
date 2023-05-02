package com.example.domacecviceniasvlastnouvahou

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class VyberPocetKlikov : AppCompatActivity() {

    private var isImageZaciatocnikSelected = false
    private var isImageStrednePokrocilySelected = false
    private var isImagePokrocilySelected = false
    private lateinit var zaciatocnik: ImageView
    private lateinit var strednePokrocily: ImageView
    private lateinit var pokrocily: ImageView
    private lateinit var buttonHotovo: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vyber_pocet_klikov)

        zaciatocnik = findViewById(R.id.zaciatocnikView)
        strednePokrocily = findViewById(R.id.strednePokrocilyView)
        pokrocily = findViewById(R.id.pokrocilyView)
        buttonHotovo = findViewById(R.id.hotovoBtn)

        zaciatocnik.setOnClickListener {
            isImageZaciatocnikSelected = !isImageZaciatocnikSelected
            if (isImageZaciatocnikSelected) {
                zaciatocnik.setBackgroundResource(R.drawable.oznacenie_obrysu)
                isImageStrednePokrocilySelected = false
                isImagePokrocilySelected = false
                strednePokrocily.setBackgroundResource(R.drawable.odznacenie_obrysu)
                pokrocily.setBackgroundResource(R.drawable.odznacenie_obrysu)
                buttonHotovo.setTextColor(Color.WHITE)
                buttonHotovo.isEnabled = true
            } else {
                zaciatocnik.setBackgroundResource(R.drawable.odznacenie_obrysu)
                buttonHotovo.setTextColor(Color.GRAY)
                buttonHotovo.isEnabled = false
            }
        }

        strednePokrocily.setOnClickListener {
            isImageStrednePokrocilySelected = !isImageStrednePokrocilySelected
            if (isImageStrednePokrocilySelected) {
                strednePokrocily.setBackgroundResource(R.drawable.oznacenie_obrysu)
                isImageZaciatocnikSelected = false
                isImagePokrocilySelected = false
                zaciatocnik.setBackgroundResource(R.drawable.odznacenie_obrysu)
                pokrocily.setBackgroundResource(R.drawable.odznacenie_obrysu)
                buttonHotovo.setTextColor(Color.WHITE)
                buttonHotovo.isEnabled = true
            } else {
                strednePokrocily.setBackgroundResource(R.drawable.odznacenie_obrysu)
                buttonHotovo.setTextColor(Color.GRAY)
                buttonHotovo.isEnabled = false
            }
        }

        pokrocily.setOnClickListener {
            isImagePokrocilySelected = !isImagePokrocilySelected
            if (isImagePokrocilySelected) {
                pokrocily.setBackgroundResource(R.drawable.oznacenie_obrysu)
                isImageZaciatocnikSelected = false
                isImageStrednePokrocilySelected = false
                zaciatocnik.setBackgroundResource(R.drawable.odznacenie_obrysu)
                strednePokrocily.setBackgroundResource(R.drawable.odznacenie_obrysu)
                buttonHotovo.setTextColor(Color.WHITE)
                buttonHotovo.isEnabled = true
            } else {
                pokrocily.setBackgroundResource(R.drawable.odznacenie_obrysu)
                buttonHotovo.setTextColor(Color.GRAY)
                buttonHotovo.isEnabled = false
            }
        }


        buttonHotovo.setOnClickListener() {
            val intent = Intent(this, Apka::class.java)
            startActivity(intent)
        }



    }
}