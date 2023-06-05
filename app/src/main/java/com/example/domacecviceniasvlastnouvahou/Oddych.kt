package com.example.domacecviceniasvlastnouvahou

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Oddych : AppCompatActivity() {

    private lateinit var countDownTimer: CountDownTimer
    private lateinit var casovacTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oddych)

        casovacTextView = findViewById(R.id.casovacTextView)

        val casNaOdpocet: Long = 10000 // 30 sekúnd

        // Inicializácia a spustenie časovača
        countDownTimer = object : CountDownTimer(casNaOdpocet, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val remainingSeconds = millisUntilFinished / 1000
                casovacTextView.text = remainingSeconds.toString()
            }

            override fun onFinish() {
                casovacTextView.text = "0"
                spustiDalsiCvik()
            }
        }.start()
    }

    private fun spustiDalsiCvik() {
        val intent = Intent(this, Trening::class.java)
        startActivity(intent)
        finish()
    }
}