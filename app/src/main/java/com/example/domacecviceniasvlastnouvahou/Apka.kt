package com.example.domacecviceniasvlastnouvahou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import java.util.concurrent.TimeUnit

class Apka : AppCompatActivity() {

    private lateinit var workoutImgViewA: ImageView
    private lateinit var casTreninguTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workoutImgViewA = findViewById(R.id.workoutImgView)
        casTreninguTextView = findViewById(R.id.casImgView)

        val casTreningu = intent.getLongExtra("casTreningu", 0)
        val formatovanyCas = formatujCas(casTreningu)

        casTreninguTextView.text = "$formatovanyCas\n čas tréningu"

        workoutImgViewA.setOnClickListener {
            val intent = Intent(this, ZoznamCvikov::class.java)
            startActivity(intent)
        }
    }

    private fun formatujCas(cas: Long): String {
        val hodiny = TimeUnit.MILLISECONDS.toHours(cas)
        val minuty = TimeUnit.MILLISECONDS.toMinutes(cas) % 60
        val sekundy = TimeUnit.MILLISECONDS.toSeconds(cas) % 60

        return String.format("%02d:%02d:%02d", hodiny, minuty, sekundy)
    }
}
