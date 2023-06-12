package com.example.domacecviceniasvlastnouvahou

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkRequest
import java.util.concurrent.TimeUnit
import androidx.work.*



/**
 * Aktivita Apka je hlavnou aktivitou aplikácie domáce cvičenia so vlastnou váhou.
 * Zobrazuje hlavné rozhranie aplikácie a obsahuje ovládacie prvky pre spustenie tréningu
 * a prechod do nastavení.
 */
class Apka : AppCompatActivity() {
    private lateinit var workoutImgViewA: ImageView
    private lateinit var casTreninguTextView: TextView
    private lateinit var nastaveniaImgView: ImageView

    /**
     * Metóda sa volá pri vytvorení inštancie aktivity Apka.
     * Nastavuje rozloženie aktivity, inicializuje ovládacie prvky,
     * načíta čas tréningu a nastaví správne formátovaný čas na TextView.
     *
     * @param savedInstanceState Uložený stav inštancie aktivity.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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

        // Vytvorenie notifikačného kanála
        val defaultNotifications = NotificationChannel("default", "Notifikacie", NotificationManager.IMPORTANCE_DEFAULT)
        defaultNotifications.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        defaultNotifications.setShowBadge(true)
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.createNotificationChannels(listOf(defaultNotifications))

    }

    /**
     * Metóda sa volá pri zničení aktivity Apka.
     * Spúšťa oznámenie o spokojnosti s aplikáciou užívateľovi.
     */
    override fun onDestroy() {
        super.onDestroy()
        notifikacia()
    }

    /**
     * Metóda vytvorí a spustí WorkRequest pre notifikáciu.
     */
    private fun notifikacia() {
        val constraints: Constraints = Constraints.Builder().build()
        val myWorkRequest: WorkRequest = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
            .setConstraints(constraints)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .build()
        WorkManager.getInstance(this).enqueue(myWorkRequest)

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