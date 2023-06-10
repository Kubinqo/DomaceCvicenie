package com.example.domacecviceniasvlastnouvahou

import Cvik
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView

/**
 * Aktivita zobrazuje cviky a riadi priebeh tréningu.
 */
class Trening : AppCompatActivity() {

    private lateinit var nazovCvikuTextView: TextView
    private lateinit var popisCvikuTextView: TextView
    private lateinit var hotovoButton: Button
    private lateinit var videoView: VideoView
    private lateinit var mediaController: MediaController
    private lateinit var cvikyList: List<Cvik>
    private var currentIndex: Int = 0
    private var casovac: CountDownTimer? = null
    private var startTime: Long = 0
    private var endTime: Long = 0
    private lateinit var settingsManager: SettingsManager

    /**
     * Metóda sa volá pri vytvorení aktivity `Trening`.
     *
     * @param savedInstanceState Uchováva stav aktivity v prípade obnovenia.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trening)

        settingsManager = SettingsManager(this)

        nazovCvikuTextView = findViewById(R.id.nazovCvikuTextViewT)
        popisCvikuTextView = findViewById(R.id.popisCvikuTextViewT)
        hotovoButton = findViewById(R.id.hotovoButton)
        videoView = findViewById(R.id.videoView)

        mediaController = MediaController(this)
        videoView.setMediaController(mediaController)

        val zoznamCvikov = ZoznamCvikov()
        cvikyList = zoznamCvikov.getCvikyList()

        zacniTrening()

        hotovoButton.setOnClickListener {
            ukonciCvik()
        }
    }

    /**
     * Táto metóda sa volá, keď je stlačené tlačidlo späť na zariadení.
     * Spúšťa aktivitu Apka a ukončuje aktuálnu aktivitu Trening.
     */
    override fun onBackPressed() {
        val intent = Intent(this, Apka::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * Metóda spúšťa tréning.
     */
    private fun zacniTrening() {
        // Získajte aktuálny čas pri začiatku tréningu
        startTime = System.currentTimeMillis()

        val mediaPlayer = MediaPlayer.create(this, R.raw.pisk)
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)

        mediaPlayer.setVolume(currentVolume.toFloat(), currentVolume.toFloat())
        mediaPlayer.start()

        zobrazCvik()
    }

    /**
     * Metóda zobrazuje aktuálny cvik a riadi jeho prehrávanie.
     */
    private fun zobrazCvik() {
        if (currentIndex < cvikyList.size) {
            val cvik = cvikyList[currentIndex]
            nazovCvikuTextView.text = cvik.nazov

            val videoResource = resources.getIdentifier(cvik.videoName, "raw", packageName)
            val videoPath = "android.resource://$packageName/$videoResource"

            mediaController = MediaController(this)
            mediaController.setMediaPlayer(videoView)
            videoView.setMediaController(mediaController)

            videoView.setVideoPath(videoPath)
            videoView.setOnPreparedListener { mediaPlayer ->
                mediaPlayer.isLooping = true
                mediaPlayer.start()

                val casovyLimit = settingsManager.getCasovyLimit() * 1000

                if (currentIndex == 0 && casovyLimit != 0) {
                    casovac = object : CountDownTimer(casovyLimit.toLong(), 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            val seconds = millisUntilFinished / 1000
                            popisCvikuTextView.text = seconds.toString()
                        }

                        override fun onFinish() {
                            videoView.stopPlayback()
                            zobrazDalsiCvik()
                        }
                    }.start()
                } else {
                    popisCvikuTextView.text = cvik.popis
                }
            }
        } else {
            videoView.stopPlayback()
            ukonciTrening()
        }
    }

    /**
     * Metóda zobrazuje nasledujúci cvik po ukončení aktuálneho.
     */
    private fun zobrazDalsiCvik() {
        currentIndex++
        val mediaPlayer = MediaPlayer.create(this, R.raw.pisk)
        mediaPlayer.start()
        zobrazCvik()
    }

    /**
     * Metóda ukončuje aktuálny cvik a prechádza na nasledujúci cvik.
     */
    private fun ukonciCvik() {
        casovac?.cancel()
        zobrazDalsiCvik()
    }

    /**
     * Metóda ukončuje tréning a prechádza späť do aktivity `Apka`.
     */
    private fun ukonciTrening() {
        // Získajte aktuálny čas pri ukončení tréningu
        endTime = System.currentTimeMillis()

        // Vypočítajte čas trvania tréningu
        val casTreningu = endTime - startTime

        // Prevezmite výsledný čas späť do aktivity Apka
        val intent = Intent(this, Apka::class.java)
        intent.putExtra("casTreningu", casTreningu)
        startActivity(intent)

        // Ukončite aktivitu Trening
        finish()
    }
}
