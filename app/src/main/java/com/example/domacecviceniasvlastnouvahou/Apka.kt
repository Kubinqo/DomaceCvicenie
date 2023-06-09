package com.example.domacecviceniasvlastnouvahou

import NotificationHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import java.util.concurrent.TimeUnit

class Apka : AppCompatActivity() {
    private lateinit var workoutImgViewA: ImageView
    private lateinit var casTreninguTextView: TextView
    private lateinit var nastaveniaImgView: ImageView
    private lateinit var notificationHelper: NotificationHelper
    private val handler = Handler(Looper.getMainLooper())
    private val checkInterval = 5 * 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationHelper = NotificationHelper(this)

        workoutImgViewA = findViewById(R.id.workoutImgView)
        nastaveniaImgView = findViewById(R.id.nastaveniaImgView)
        casTreninguTextView = findViewById(R.id.casImgView)

        val casTreningu = intent.getLongExtra("casTreningu", 0)
        val formatovanyCas = formatujCas(casTreningu)

        casTreninguTextView.text = "čas tréningu: $formatovanyCas"

        workoutImgViewA.setOnClickListener {
            animateImageView(workoutImgViewA)

            val intent = Intent(this, ZoznamCvikov::class.java)
            startActivity(intent)
            finish()
        }

        nastaveniaImgView.setOnClickListener {
            animateImageView(nastaveniaImgView)
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }
    }
    override fun onStop() {
        super.onStop()
        startSatisfactionCheck()
    }
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(checkSatisfactionRunnable)
    }

    private val checkSatisfactionRunnable = object : Runnable {
        override fun run() {
            checkSatisfaction()
            handler.postDelayed(this, checkInterval.toLong())
        }
    }
    private fun startSatisfactionCheck() {
        handler.postDelayed(checkSatisfactionRunnable, checkInterval.toLong())
    }
    private fun checkSatisfaction() {
        notificationHelper.showAppSatisfactionNotification()
    }
    private fun animateImageView(imageView: ImageView) {
        val scaleDownAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_down)
        val scaleUpAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_up)

        // Zachyťte udalosti animácie pre vrátenie obrazka do pôvodnej veľkosti po skončení animácie zväčšenia
        scaleUpAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                // Nastavenia po skončení animácie zväčšenia (napr. zmena zdroja obrazka)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        imageView.startAnimation(scaleDownAnimation)
        imageView.startAnimation(scaleUpAnimation)
    }
    private fun formatujCas(cas: Long): String {
        val hodiny = TimeUnit.MILLISECONDS.toHours(cas)
        val minuty = TimeUnit.MILLISECONDS.toMinutes(cas) % 60
        val sekundy = TimeUnit.MILLISECONDS.toSeconds(cas) % 60

        return String.format("%02d:%02d:%02d", hodiny, minuty, sekundy)
    }
}