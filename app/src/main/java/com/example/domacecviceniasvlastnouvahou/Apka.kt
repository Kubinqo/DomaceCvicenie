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

/**
 * Aktivita Apka je hlavnou aktivitou aplikácie domáce cvičenia so vlastnou váhou.
 * Zobrazuje hlavné rozhranie aplikácie a obsahuje ovládacie prvky pre spustenie tréningu
 * a prechod do nastavení.
 */
class Apka : AppCompatActivity() {
    private lateinit var workoutImgViewA: ImageView
    private lateinit var casTreninguTextView: TextView
    private lateinit var nastaveniaImgView: ImageView
    private lateinit var notificationHelper: NotificationHelper
    private val handler = Handler(Looper.getMainLooper())
    private val checkInterval = 5 * 1000

    /**
     * Metóda sa volá pri vytvorení inštancie aktivity Apka.
     * Nastavuje rozloženie aktivity, inicializuje ovládacie prvky,
     * načíta čas tréningu a nastaví správne formátovaný čas na TextView.
     *
     * @param savedInstanceState Uložený stav inštancie aktivity.
     */
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

    /**
    * Metóda sa volá pri zastavení aktivity Apka.
    * Spúšťa kontrolu spokojnosti a nastavuje interval pre kontrolu.
    */
    override fun onStop() {
        super.onStop()
        startSatisfactionCheck()
    }

    /**
     * Metóda sa volá pri zničení aktivity Apka.
     * Odstraňuje spúšťateľný objekt pre kontrolu spokojnosti z handlera.
     */
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(checkSatisfactionRunnable)
    }

    /**
     * Vykonáva kontrolu spokojnosti a nastavuje opakované spustenie po určenom časovom intervale.
     */
    private val checkSatisfactionRunnable = object : Runnable {
        override fun run() {
            checkSatisfaction()
            handler.postDelayed(this, checkInterval.toLong())
        }
    }

    /**
     * Nastavuje spúšťateľný objekt pre kontrolu spokojnosti s určeným časovým intervalom.
     */
    private fun startSatisfactionCheck() {
        handler.postDelayed(checkSatisfactionRunnable, checkInterval.toLong())
    }

    /**
     * Zobrazuje oznámenie o spokojnosti pomocou NotificationHelper.
     */
    private fun checkSatisfaction() {
        notificationHelper.showAppSatisfactionNotification()
    }

    /**
     * Animuje ImageView.
     * Načíta animácie zo zdrojov, spúšťa animáciu zmenšenia a následne animáciu zväčšenia ImageView.
     *
     * @param imageView ImageView, ktorého animácia sa má spustiť.
     */
    private fun animateImageView(imageView: ImageView) {
        val scaleDownAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_down)
        val scaleUpAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_up)

        // Zachyti udalosti animacie pre vratenie obrazka do povodnej velkosti
        scaleUpAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
        })
        imageView.startAnimation(scaleDownAnimation)
        imageView.startAnimation(scaleUpAnimation)
    }

    /**
     * Formátuje čas v milisekundách na reťazec vo formáte "hh:mm:ss".
     *
     * @param cas Čas v milisekundách.
     * @return Formátovaný čas vo formáte "hh:mm:ss".
     */
    private fun formatujCas(cas: Long): String {
        val hodiny = TimeUnit.MILLISECONDS.toHours(cas)
        val minuty = TimeUnit.MILLISECONDS.toMinutes(cas) % 60
        val sekundy = TimeUnit.MILLISECONDS.toSeconds(cas) % 60

        return String.format("%02d:%02d:%02d", hodiny, minuty, sekundy)
    }
}